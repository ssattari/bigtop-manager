package org.apache.bigtop.manager.stack.core.hooks;


import com.google.auto.service.AutoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.bigtop.manager.stack.core.annotations.HookAnnotation;
import org.apache.bigtop.manager.stack.spi.Hook;

/**
 * obtain agent execute command
 */
@Slf4j
@AutoService(Hook.class)
public class StartHookImpl implements Hook {


    @Override
    @HookAnnotation(before = "any")
    public void before() {
        log.info("before start");
    }

    @Override
    @HookAnnotation(after = "any")
    public void after() {
        log.info("after start");
    }

    @Override
    public String getName() {
        return "start";
    }
}