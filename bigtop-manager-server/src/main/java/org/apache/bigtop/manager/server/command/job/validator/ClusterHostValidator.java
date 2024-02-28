package org.apache.bigtop.manager.server.command.job.validator;

import jakarta.annotation.Resource;
import org.apache.bigtop.manager.common.enums.Command;
import org.apache.bigtop.manager.server.enums.ApiExceptionEnum;
import org.apache.bigtop.manager.server.enums.CommandLevel;
import org.apache.bigtop.manager.server.exception.ApiException;
import org.apache.bigtop.manager.server.command.CommandIdentifier;
import org.apache.bigtop.manager.server.holder.SpringContextHolder;
import org.apache.bigtop.manager.server.model.dto.command.ClusterCommandDTO;
import org.apache.bigtop.manager.dao.entity.Host;
import org.apache.bigtop.manager.dao.repository.HostRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClusterHostValidator implements CommandValidator {

    @Resource
    private HostRepository hostRepository;

    @Override
    public List<CommandIdentifier> getCommandIdentifiers() {
        return List.of(new CommandIdentifier(CommandLevel.CLUSTER, Command.CREATE));
    }

    @Override
    public void validate(ValidatorContext context) {
        ClusterCommandDTO clusterCommand = context.getCommandDTO().getClusterCommand();
        List<String> hostnames = clusterCommand.getHostnames();

        List<String> connectedHosts = SpringContextHolder.getServerWebSocket().getConnectedHosts();
        if (CollectionUtils.isNotEmpty(connectedHosts)) {
            List<String> notConnectedHostnames = hostnames.stream().filter(hostname -> !connectedHosts.contains(hostname)).toList();
            if (CollectionUtils.isNotEmpty(notConnectedHostnames)) {
                throw new ApiException(ApiExceptionEnum.HOST_NOT_CONNECTED, String.join(",", notConnectedHostnames));
            }
        }

        List<Host> hosts = hostRepository.findAllByHostnameIn(hostnames);
        if (CollectionUtils.isNotEmpty(hosts)) {
            List<String> existsHostnames = hosts.stream().map(Host::getHostname).toList();
            throw new ApiException(ApiExceptionEnum.HOST_ASSIGNED, String.join(",", existsHostnames));
        }
    }

}
