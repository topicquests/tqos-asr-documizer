/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.documizer;


import org.topicquests.asr.documizer.api.IDocumizerModel;
import org.topicquests.ks.SystemEnvironment;
import org.topicquests.ks.api.ITicket;
import org.topicquests.ks.tm.api.IDataProvider;
import org.topicquests.ks.tm.api.IProxy;
import org.topicquests.ks.tm.api.IProxyModel;
import org.topicquests.os.asr.ASRCoreModel;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class DocumizerModel extends ASRCoreModel implements IDocumizerModel {
	private IProxyModel proxyModel;
	private IDataProvider database = null;

	/**
	 * @param env
	 * @param c
	 */
	public DocumizerModel(DocumizerEnvironment env) {
		super(env);
		SystemEnvironment tmEnv = new SystemEnvironment();
		proxyModel = tmEnv.getProxyModel();
		database = tmEnv.getDataProvider();

		environment.logDebug("DocumizerModel- "+documentProvider);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.documizer.api.IDocumizerModel#getNode(java.lang.String, org.topicquests.ks.api.ITicket)
	 */
	@Override
	public IResult getNode(String locator, ITicket credentials) {
		return database.getFullNode(locator, credentials);
	}

	/* (non-Javadoc)
	 * @see org.topicquests.asr.documizer.api.IDocumizerModel#putNode(org.topicquests.ks.tm.api.IProxy)
	 */
	@Override
	public IResult putNode(IProxy node) {
		return database.putNode(node);
	}


}
