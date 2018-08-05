package com.self.practice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent;
import com.amazonaws.services.lambda.runtime.events.KinesisFirehoseEvent.Record;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.Base64;
import com.amazonaws.util.IOUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class FirehoseEvent2CSV implements RequestHandler<KinesisFirehoseEvent, KinesisFirehoseResponse> {

	KinesisFirehoseResponse kfr = new KinesisFirehoseResponse();

	public KinesisFirehoseResponse handleRequest(KinesisFirehoseEvent event, Context arg1) {

		for (Record rec : event.getRecords()) {
			try {

				String srcData = new String(rec.getData().array(), "UTF-8");
				KinesisFirehoseResponse.FirehoseRecord record = new KinesisFirehoseResponse().new FirehoseRecord();

				record.setRecordId(rec.getRecordId());
				record.setResult("Ok");

				String tgtData = parseJson(srcData);

				record.encodAndSetData(tgtData);

				System.out.println(tgtData);
				kfr.records.add(record);
				return kfr;

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}

		return kfr;

	}

	private String parseJson(String srcData) {
		// TODO Auto-generated method stub
		Gson g = new Gson();
		StringReader strReader = new StringReader(srcData);

		JsonReader jsonReader = new JsonReader(strReader);

		SampleClass sc = g.fromJson(jsonReader, SampleClass.class);
		return (sc.getTicker_symbol() + "," + sc.getSector() + "," + sc.getPrice() + "\n");

	}

}
