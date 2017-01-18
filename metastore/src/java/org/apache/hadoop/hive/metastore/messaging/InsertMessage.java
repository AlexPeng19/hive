/**
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

package org.apache.hadoop.hive.metastore.messaging;

import java.util.List;
import java.util.Map;

/**
 * HCat message sent when an insert is done to a table or partition.
 */
public abstract class InsertMessage extends EventMessage {

  protected InsertMessage() {
    super(EventType.INSERT);
  }

  /**
   * Getter for the name of the table being insert into.
   * @return Table-name (String).
   */
  public abstract String getTable();

  /**
   * Get the map of partition keyvalues.  Will be null if this insert is to a table and not a
   * partition.
   * @return Map of partition keyvalues, or null.
   */
  public abstract Map<String,String> getPartitionKeyValues();

  /**
   * Get the list of files created as a result of this DML operation. May be null. The file uri may
   * be an encoded uri, which represents both a uri and the file checksum
   *
   * @return List of new files, or null.
   */
  public abstract List<String> getFiles();

  @Override
  public EventMessage checkValid() {
    if (getTable() == null)
      throw new IllegalStateException("Table name unset.");
    return super.checkValid();
  }
}