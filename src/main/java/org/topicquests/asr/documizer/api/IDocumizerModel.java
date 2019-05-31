/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.asr.documizer.api;

import org.topicquests.ks.api.ITicket;
import org.topicquests.ks.tm.api.IProxy;
import org.topicquests.os.asr.api.IASRCoreModel;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public interface IDocumizerModel extends IASRCoreModel {

	/**
	 * Fetch a topic map node identified by <code>locator</code>
	 * @param locator
	 * @param credentials ignored: can be <code>null</code>
	 * @return
	 */
	IResult getNode(String locator, ITicket credentials);

	/**
	 * Store a topic map node
	 * @param node
	 * @return
	 */
	IResult putNode(IProxy node);

}
