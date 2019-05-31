/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.documizer;

import org.topicquests.asr.documizer.api.IDocumizerModel;
import org.topicquests.asr.documizer.importer.JSONDocumentReader;
import org.topicquests.asr.documizer.importer.JSONFileReader;
import org.topicquests.os.asr.ASRCoreEnvironment;
import org.topicquests.os.asr.api.IDocumentProvider;

/**
 * @author jackpark
 *
 */
public class DocumizerEnvironment extends ASRCoreEnvironment {
	private IDocumentProvider docProvider;
	private IDocumizerModel model;
	private JSONDocumentReader jsonDocReader;
	private JSONFileReader jsonFileReader;

	/**
	 * ASRCoreEnvironment reads "asr-props.xml"
	 */
	public DocumizerEnvironment() {
		try {
			model = new DocumizerModel(this);
			docProvider = new DocumentProvider(this);
			jsonDocReader = new JSONDocumentReader(this, model);
		} catch (Exception e) {
			logError(e.getMessage(), e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		jsonFileReader = new JSONFileReader(this, jsonDocReader);
	}

	public JSONFileReader getJSONFileReader() {
		return jsonFileReader;
	}
	
	public JSONDocumentReader getJSONDocumentReader() {
		return jsonDocReader;
	}
		
	public IDocumizerModel getModel() {
		return model;
	}
	
	public IDocumentProvider getDocumentProvider() {
		return docProvider;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DocumizerEnvironment();
	}

}
