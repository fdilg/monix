/*
 * Copyright (c) 2014 by its authors. Some rights reserved.
 * See the project homepage at
 *
 *     http://www.monifu.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package monifu.reactive.tck

import monifu.concurrent.Implicits.globalScheduler
import monifu.reactive.Observable
import org.reactivestreams.Publisher
import org.reactivestreams.tck.{PublisherVerification, TestEnvironment}
import org.scalatest.testng.TestNGSuiteLike

class PublisherFlatMapTest
  extends PublisherVerification[Int](new TestEnvironment(1000), 1000) with TestNGSuiteLike {

  def createPublisher(elements: Long): Publisher[Int] = {
    val obs = Observable.repeat(1).flatMap(x => Observable.unit(x))

    if (elements == Long.MaxValue)
      obs.publisher
    else
      obs.take(elements.toInt).publisher
  }

  def createErrorStatePublisher(): Publisher[Int] = {
    Observable.error(new RuntimeException).publisher[Int]
  }
}