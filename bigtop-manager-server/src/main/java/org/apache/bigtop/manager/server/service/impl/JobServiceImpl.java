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
package org.apache.bigtop.manager.server.service.impl;

import org.apache.bigtop.manager.dao.entity.Job;
import org.apache.bigtop.manager.dao.repository.JobRepository;
import org.apache.bigtop.manager.server.model.mapper.JobMapper;
import org.apache.bigtop.manager.server.model.vo.JobVO;
import org.apache.bigtop.manager.server.service.JobService;

import java.util.List;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    private JobRepository jobRepository;

    @Override
    public List<JobVO> list(Long clusterId) {
        // PageQuery pageQuery = PageUtils.getPageQuery();
        // Pageable pageable = PageRequest.of(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.getSort());
        // Page<Job> page;
        // if (ClusterUtils.isNoneCluster(clusterId)) {
        // page = jobRepository.findAllByClusterIsNull(pageable);
        // } else {
        // page = jobRepository.findAllByClusterId(clusterId, pageable);
        // }

        List<Job> jobs = jobRepository.findAllByClusterId(clusterId);

        return JobMapper.INSTANCE.fromEntity2VO(jobs);
    }

    @Override
    public JobVO get(Long id) {
        Job job = jobRepository.getReferenceById(id);
        return JobMapper.INSTANCE.fromEntity2VO(job);
    }
}
