package org.apache.bigtop.manager.server.orm.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@TableGenerator(name = "cluster_generator", table = "sequence")
public class Cluster {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cluster_generator")
    private Long id;

    private String clusterName;

    private Integer clusterType;

    private Timestamp createTime;

    private Timestamp updateTime;
}