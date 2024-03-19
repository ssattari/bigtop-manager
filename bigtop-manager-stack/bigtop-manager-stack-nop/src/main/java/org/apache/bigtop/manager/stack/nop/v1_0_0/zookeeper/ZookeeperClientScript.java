package org.apache.bigtop.manager.stack.nop.v1_0_0.zookeeper;


import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.bigtop.manager.common.shell.ShellResult;
import org.apache.bigtop.manager.spi.stack.ClientScript;
import org.apache.bigtop.manager.spi.stack.Params;
import org.apache.bigtop.manager.spi.stack.Script;

@Slf4j
@AutoService(Script.class)
public class ZookeeperClientScript implements ClientScript {

    @Override
    public ShellResult install(Params params) {
        return ShellResult.success();
    }

    @Override
    public ShellResult configure(Params params) {
        return ShellResult.success();
    }

}
