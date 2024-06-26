/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.bigtop.manager.server.model.mapper;

import org.apache.bigtop.manager.dao.entity.ServiceConfig;
import org.apache.bigtop.manager.server.model.vo.ServiceConfigVO;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TypeConfigMapper.class})
public interface ServiceConfigMapper {

    ServiceConfigMapper INSTANCE = Mappers.getMapper(ServiceConfigMapper.class);

    @Mapping(target = "serviceName", source = "service.serviceName")
    @Mapping(target = "configs", source = "configs", qualifiedByName = "fromEntity2VO")
    ServiceConfigVO fromEntity2VO(ServiceConfig serviceConfig);

    List<ServiceConfigVO> fromEntity2VO(List<ServiceConfig> serviceConfigs);
}
