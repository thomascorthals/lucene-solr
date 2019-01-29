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

package org.apache.solr.cloud.api.collections;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrResponse;
import org.apache.solr.cloud.Overseer;
import org.apache.solr.common.cloud.ClusterState;
import org.apache.solr.common.cloud.ZkNodeProps;
import org.apache.solr.common.params.CollectionParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.handler.admin.CollectionsHandler;

public class MaintainCategoryRoutedAliasCmd extends AliasCmd {

  public static final String IF_CATEGORY_COLLECTION_NOT_FOUND = "ifCategoryCollectionNotFound";

  /**
   * Invokes this command from the client.  If there's a problem it will throw an exception.
   * Please note that is important to never add async to this invocation. This method must
   * block (up to the standard OCP timeout) to prevent large batches of add's from sending a message
   * to the overseer for every document added in RoutedAliasUpdateProcessor.
   */
  public static NamedList remoteInvoke(CollectionsHandler collHandler, String aliasName, String categoryCollection)
      throws Exception {
    final String operation = CollectionParams.CollectionAction.MAINTAINCATEGORYROUTEDALIAS.toLower();
    Map<String, Object> msg = new HashMap<>();
    msg.put(Overseer.QUEUE_OPERATION, operation);
    msg.put(CollectionParams.NAME, aliasName);
    msg.put(IF_CATEGORY_COLLECTION_NOT_FOUND, categoryCollection);
    final SolrResponse rsp = collHandler.sendToOCPQueue(new ZkNodeProps(msg));
    if (rsp.getException() != null) {
      throw rsp.getException();
    }
    return rsp.getResponse();
  }

  @Override
  public void call(ClusterState state, ZkNodeProps message, NamedList results) throws Exception {
    //todo
  }
}
