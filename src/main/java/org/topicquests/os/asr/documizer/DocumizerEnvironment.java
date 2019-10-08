/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.documizer;

import org.topicquests.asr.general.GeneralDatabaseEnvironment;
import org.topicquests.asr.general.document.api.IDocumentClient;
import org.topicquests.asr.sentence.api.ISentenceClient;
import org.topicquests.os.asr.DocumentProvider;
import org.topicquests.os.asr.StatisticsHttpClient;
//import org.topicquests.os.asr.SentenceProvider;
import org.topicquests.os.asr.api.IDocumentProvider;
//import org.topicquests.os.asr.api.ISentenceProvider;
import org.topicquests.os.asr.api.IStatisticsClient;
//import org.topicquests.os.asr.documizer.api.IDocumizerModel;
import org.topicquests.os.asr.documizer.importer.JSONDocumentReader;
import org.topicquests.os.asr.documizer.importer.JSONFileReader;
import org.topicquests.support.RootEnvironment;

/**
 * @author jackpark
 *
 */
public class DocumizerEnvironment extends RootEnvironment {
	private static DocumizerEnvironment instance;
	private JSONDocumentReader jsonDocReader;
	private JSONFileReader jsonFileReader;
//	private ISentenceProvider sentenceProvider;
	private IDocumentProvider documentProvider;
	private IDocumentClient documentDatabase;
//	private ISentenceClient sentenceDatabase;
	private GeneralDatabaseEnvironment generalEnvironment;
	private IStatisticsClient stats;

	/**
	 * ASRCoreEnvironment reads "asr-props.xml"
	 */
	public DocumizerEnvironment() {
		super("asr-props.xml", "logger.properties");
 		stats = new StatisticsHttpClient(this);
		String schemaName = getStringProperty("DatabaseSchema");
		generalEnvironment = new GeneralDatabaseEnvironment(schemaName);
		documentDatabase = generalEnvironment.getDocumentClient();
		documentProvider = new DocumentProvider(this);

		try {
			jsonDocReader = new JSONDocumentReader(this);
		} catch (Exception e) {
			logError(e.getMessage(), e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		jsonFileReader = new JSONFileReader(this, jsonDocReader);
		instance = this;
	}

	public static DocumizerEnvironment getInstance() {
		return instance;
	}
	
	public IStatisticsClient getStats() {
	    return stats;
	}

	public GeneralDatabaseEnvironment getGeneralDatabaseEnvironment() {
		return generalEnvironment;
	}
	public IDocumentProvider getDocProvider() {
		return documentProvider;
	}
	public IDocumentClient getDocumentDatabase () {
		return documentDatabase;
	}
/*	public ISentenceClient getSentenceDatabase() {
		return sentenceDatabase;
	}

	public ISentenceProvider getSentenceProvider() {
		return sentenceProvider;
	}
*/	
	public JSONFileReader getJSONFileReader() {
		return jsonFileReader;
	}
	
	public JSONDocumentReader getJSONDocumentReader() {
		return jsonDocReader;
	}
		
/*	public IDocumizerModel getModel() {
		return model;
	}*/
	
	public void shutDown() {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DocumizerEnvironment();
	}

}
