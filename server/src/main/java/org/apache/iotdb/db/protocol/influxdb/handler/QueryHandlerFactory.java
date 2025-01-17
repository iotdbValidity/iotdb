/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.db.protocol.influxdb.handler;

import org.apache.iotdb.db.conf.IoTDBDescriptor;
import org.apache.iotdb.db.service.thrift.impl.ClientRPCServiceImpl;

/** Generate the corresponding QueryHandler object according to the configuration */
public class QueryHandlerFactory {

  /**
   * get QueryHandler object according to the configuration
   *
   * @return QueryHandler object
   */
  public static AbstractQueryHandler getInstance() {
    if (IoTDBDescriptor.getInstance()
        .getConfig()
        .getRpcImplClassName()
        .equals(ClientRPCServiceImpl.class.getName())) {
      if ("Tag".equals(IoTDBDescriptor.getInstance().getConfig().getSchemaEngineMode())) {
        return new TagQueryHandler();
      }
      return new NewQueryHandler();
    } else {
      return new QueryHandler();
    }
  }
}
