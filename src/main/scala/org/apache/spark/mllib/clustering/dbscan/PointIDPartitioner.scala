/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.mllib.clustering.dbscan

import org.apache.spark.Partitioner
import archery.Point
import org.apache.spark.HashPartitioner

class PointIDPartitioner(partitions: Int) extends HashPartitioner(partitions) {

  override def getPartition(key: Any): Int = key match {
    case (p, i) => (p, i) match {
      case (p: Point, partition: Int) => partition
    }
    case _ => super.getPartition(key)
  }

  override def equals(other: Any): Boolean = other match {
    case h: PointIDPartitioner =>
      h.numPartitions == numPartitions
    case _ => super.equals(other)
  }

  override def hashCode: Int = numPartitions

}