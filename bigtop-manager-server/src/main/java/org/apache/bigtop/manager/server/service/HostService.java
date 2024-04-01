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
package org.apache.bigtop.manager.server.service;

import org.apache.bigtop.manager.server.model.dto.HostDTO;
import org.apache.bigtop.manager.server.model.vo.HostVO;

import java.util.List;

public interface HostService {

    /**
     * Get all hosts.
     *
     * @return Hosts
     */
    List<HostVO> list(Long clusterId);

    /**
     * Save a host
     *
     * @return Host
     */
    List<HostVO> batchSave(Long clusterId, List<String> hostnames);

    /**
     * Get a host
     *
     * @return Host
     */
    HostVO get(Long id);

    /**
     * Update a host
     *
     * @return Host
     */
    HostVO update(Long id, HostDTO hostDTO);

    /**
     * Delete a host
     *
     * @return Host
     */
    Boolean delete(Long id);
}
