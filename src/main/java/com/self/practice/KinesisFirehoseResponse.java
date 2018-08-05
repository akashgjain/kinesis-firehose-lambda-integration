package com.self.practice;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.util.Base64;

public class KinesisFirehoseResponse {

	public List<FirehoseRecord> getRecords() {
		return records;
	}

	public void setRecords(List<FirehoseRecord> records) {
		this.records = records;
	}

	List<FirehoseRecord> records = new ArrayList<FirehoseRecord>();

	class FirehoseRecord {
		String recordId="";

		public String getRecordId() {
			return recordId;
		}

		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		String result;
		String data;

		void encodAndSetData(String unencodedString) {
			data = new String(Base64.encode(unencodedString.getBytes()));

		}

	}

}
