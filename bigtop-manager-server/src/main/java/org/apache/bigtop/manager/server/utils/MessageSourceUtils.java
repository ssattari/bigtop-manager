/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.bigtop.manager.server.utils;

import org.apache.bigtop.manager.server.enums.LocaleKeys;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtils implements MessageSourceAware {

    private static MessageSource messageSource;

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        MessageSourceUtils.messageSource = messageSource;
    }

    public static String getMessage(LocaleKeys localeKeys) {
        return getMessage(localeKeys, (String) null);
    }

    public static String getMessage(LocaleKeys localeKeys, String... args) {
        return messageSource.getMessage(localeKeys.getKey(), args, LocaleContextHolder.getLocale());
    }
}
