/*
 * Copyright 2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.alloydb;

// [START alloydb_hikaricp_auto_iam_authn]
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class AlloyDbJdbcAutoIAMAuthNDataSourceFactory {

  public static final String ALLOYDB_USER = System.getenv("ALLOYDB_IAM_USER");
  public static final String ALLOYDB_INSTANCE_NAME = System.getenv("ALLOYDB_INSTANCE_NAME");

  static HikariDataSource createDataSource() {
    HikariConfig config = new HikariConfig();

    config.setJdbcUrl("jdbc:postgresql:///postgres");
    config.setUsername(ALLOYDB_USER);
    config.addDataSourceProperty("socketFactory", "com.google.cloud.alloydb.SocketFactory");
    // e.g., "projects/my-project/locations/us-central1/clusters/my-cluster/instances/my-instance"
    config.addDataSourceProperty("alloydbInstanceName", ALLOYDB_INSTANCE_NAME);
    config.addDataSourceProperty("alloydbEnableIAMAuth", "true");

    return new HikariDataSource(config);
  }
}
// [END alloydb_hikaricp_auto_iam_authn]