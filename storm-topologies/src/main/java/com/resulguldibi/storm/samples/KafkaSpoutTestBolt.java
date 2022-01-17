/*
 * Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.resulguldibi.storm.samples;

import java.io.IOException;
import java.util.Map;

import okhttp3.*;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaSpoutTestBolt extends BaseRichBolt {
    protected static final Logger LOG = LoggerFactory.getLogger(KafkaSpoutTestBolt.class);
    private OutputCollector collector;
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void prepare(Map<String, Object> topoConf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        this.client = new OkHttpClient();
    }

    @Override
    public void execute(Tuple input) {
        //LOG.error("input = [" + input + "]");
        long offset = Long.parseLong(input.getValueByField("offset").toString());
        String value = input.getValueByField("value").toString();

        LOG.info( "offset : {}, message : {}",offset,value);

        String json = "{\"id\":"+offset+",\"name\":\""+value+"\"}";
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("http://localhost:8080/customers")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Call call = client.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            response.close();
            collector.ack(input);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            collector.fail(input);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }
}