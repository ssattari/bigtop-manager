package org.apache.bigtop.manager.server.command.stage.factory.host;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.bigtop.manager.common.enums.Command;
import org.apache.bigtop.manager.common.enums.MessageType;
import org.apache.bigtop.manager.common.message.type.HostCheckPayload;
import org.apache.bigtop.manager.common.message.type.RequestMessage;
import org.apache.bigtop.manager.common.message.type.pojo.HostCheckType;
import org.apache.bigtop.manager.common.utils.JsonUtils;
import org.apache.bigtop.manager.server.command.stage.factory.AbstractStageFactory;
import org.apache.bigtop.manager.server.command.stage.factory.StageType;
import org.apache.bigtop.manager.dao.entity.Cluster;
import org.apache.bigtop.manager.dao.entity.Task;
import org.apache.bigtop.manager.dao.repository.ClusterRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@org.springframework.stereotype.Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HostCheckStageFactory extends AbstractStageFactory {

    @Resource
    private ClusterRepository clusterRepository;

    public StageType getStageType() {
        return StageType.HOST_CHECK;
    }

    @Override
    public void doCreateStage() {
        if (context.getClusterId() != null) {
            Cluster cluster = clusterRepository.getReferenceById(context.getClusterId());

            context.setStackName(cluster.getStack().getStackName());
            context.setStackVersion(cluster.getStack().getStackVersion());
        }

        // Create stages
        stage.setName("Check Hosts");

        List<Task> tasks = new ArrayList<>();
        for (String hostname : context.getHostnames()) {
            Task task = new Task();
            task.setName("Check host for " + hostname);
            task.setStackName(context.getStackName());
            task.setStackVersion(context.getStackVersion());
            task.setHostname(hostname);
            task.setServiceName("cluster");
            task.setServiceUser("root");
            task.setServiceGroup("root");
            task.setComponentName("bigtop-manager-agent");
            task.setCommand(Command.CUSTOM_COMMAND);
            task.setCustomCommand("check_host");

            RequestMessage requestMessage = createMessage(hostname);
            task.setContent(JsonUtils.writeAsString(requestMessage));
            task.setMessageId(requestMessage.getMessageId());

            tasks.add(task);
        }

        stage.setTasks(tasks);
    }

    private RequestMessage createMessage(String hostname) {
        HostCheckPayload messagePayload = new HostCheckPayload();
        messagePayload.setHostCheckTypes(HostCheckType.values());
        messagePayload.setHostname(hostname);

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMessageType(MessageType.HOST_CHECK);
        requestMessage.setHostname(hostname);
        requestMessage.setMessagePayload(JsonUtils.writeAsString(messagePayload));

        return requestMessage;
    }
}