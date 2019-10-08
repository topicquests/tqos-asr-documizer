/*
 * Copyright 2018 TopicQuests Foundation
 *  This source code is available under the terms of the Affero General Public License v3.
 *  Please see LICENSE.txt for full license terms, including the availability of proprietary exceptions.
 */
package org.topicquests.os.asr.documizer.importer;

import org.topicquests.hyperbrane.ConcordanceDocument;
import org.topicquests.hyperbrane.api.IDocument;

import org.topicquests.ks.TicketPojo;
import org.topicquests.ks.api.ITQCoreOntology;
import org.topicquests.ks.api.ITicket;

import org.topicquests.os.asr.api.IDocumentProvider;
import org.topicquests.os.asr.api.IStatisticsClient;
import org.topicquests.os.asr.common.api.IASRFields;
import org.topicquests.os.asr.documizer.DocumizerEnvironment;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class JSONDocumentReader {
	private DocumizerEnvironment environment;
	private IDocumentProvider provider;
	private IStatisticsClient stats;
	private ITicket credentials;


	/**
	 * 
	 */
	public JSONDocumentReader(DocumizerEnvironment env) {
		environment = env;
		provider = environment.getDocProvider();
		stats = environment.getStats();
		credentials = new TicketPojo(ITQCoreOntology.SYSTEM_USER);

	}
	/**
	{
		"crtr": "SystemUser",
		"pubMonth": "Mar",
		"abstract": "Frequent oncogenic alterations occur in the phosphoinositide 3-kinase (PI3K) pathway, urging identification of novel negative controls. We previously reported an original mechanism for restraining PI3K activity, controlled by the somatostatin G protein-coupled receptor (GPCR) sst2 and involving a ligand-regulated interaction between sst2 with the PI3K regulatory p85 subunit. We here identify the scaffolding protein filamin A (FLNA) as a critical player regulating the dynamic of this complex. A preexisting sst2-p85 complex, which was shown to account for a significant basal PI3K activity in the absence of ligand, is disrupted upon sst2 activation. FLNA was here identified as a competitor of p85 for direct binding to two juxtaposed sites on sst2. Switching of GPCR binding preference from p85 toward FLNA is determined by changes in the tyrosine phosphorylation of p85- and FLNA-binding sites on sst2 upon activation. It results in the disruption of the sst2-p85 complex and the subsequent inhibition of PI3K. Knocking down FLNA expression, or abrogating FLNA recruitment to sst2, reversed the inhibition of PI3K and of tumor growth induced by sst2. Importantly, we report that this FLNA inhibitory control on PI3K can be generalized to another GPCR, the mu opioid receptor, thereby providing an unprecedented mechanism underlying GPCR-negative control on PI3K.]n",
		"pmid": "22203038",
		"publicationTitle": "Molecular and cellular biology",
		"title": "A switch of G protein-coupled receptor binding preference from phosphoinositide 3-kinase (PI3K)-p85 to filamin A negatively controls the PI3K pathway.",
		"volume": "32",
		"tagList": ["Animals", "Binding Sites", "Binding, Competitive", "Cell Line", "Class Ia Phosphatidylinositol 3-Kinase", "metabolism", "Contractile Proteins", "Filamins", "Microfilament Proteins", "Phosphorylation", "Protein Binding", "Protein Subunits", "genetics", "Receptors, G-Protein-Coupled", "Signal Transduction"],
		"pages": "1004-16",
		"citations": ["11425302", "21699508", "11390380", "11739414", "12024020", "12414637", "12490654", "12531889", "12665520", "12972574", "12878607", "14573758", "14654798", "15001578", "15464590", "1549777", "7878022", "8620499", "9041201", "9412467", "15516996", "15657428", "15608144", "15623510", "15657061", "10900262", "11320256", "15917206", "15837785", "15901846", "16455489", "16293600", "16611986", "16781869", "16878972", "16917505", "16959974", "17235394", "17606711", "17629961", "17420725", "18281671", "17913342", "18359151", "18550856", "18794886", "18829455", "19001514", "19690385", "19935667", "20332112", "20414202", "20937704", "21193867", "21169733", "21926999", "11337495"],
		"lox": "PubMed22203038",
		"issn": "0270-7306",
		"pubYear": "2012",
		"substanceList": ["Contractile Proteins", "Filamins", "Microfilament Proteins", "Protein Subunits", "Receptors, G-Protein-Coupled", "Class Ia Phosphatidylinositol 3-Kinase"],
		"publisher": "Mol Cell Biol",
		"pubType": "RshSupNonGovType",
		"authors": [{
			"lName": "Najib",
			"fName": "Najib, Souad",
			"affiliation": "INSERM UMR 1037, Centre de Recherche en CancÃ©rologie de Toulouse, Toulouse, France.",
			"initials": "S"
		}, {
			"lName": "Saint-Laurent",
			"fName": "Saint-Laurent, Nathalie",
			"initials": "N"
		}, {
			"lName": "EstÃ¨ve",
			"fName": "EstÃ¨ve, Jean-Pierre",
			"initials": "JP"
		}, {
			"lName": "Schulz",
			"fName": "Schulz, Stefan",
			"initials": "S"
		}, {
			"lName": "Boutet-Robinet",
			"fName": "Boutet-Robinet, Elisa",
			"initials": "E"
		}, {
			"lName": "Fourmy",
			"fName": "Fourmy, Daniel",
			"initials": "D"
		}, {
			"lName": "LÃ¤ttig",
			"fName": "LÃ¤ttig, Jens",
			"initials": "J"
		}, {
			"lName": "Mollereau",
			"fName": "Mollereau, Catherine",
			"initials": "C"
		}, {
			"lName": "Pyronnet",
			"fName": "Pyronnet, StÃ©phane",
			"initials": "S"
		}, {
			"lName": "Susini",
			"fName": "Susini, Christiane",
			"initials": "C"
		}, {
			"lName": "Bousquet",
			"fName": "Bousquet, Corinne",
			"initials": "C"
		}]
	} 
	 */	
	/**
	 * Do the work: import the contents of <code>jo</code>
	 * @param jo
	 */ // code here adapted from PubMedImporter.Phase2Engine
	public void processJSON(JSONObject jo) {
		environment.logDebug("JSONDocumentReader.processJSON "+jo);
		IDocument doc = new ConcordanceDocument(jo);
		String lox = doc.getId();
		String pmcid = doc.getPMCID();
		if (pmcid != null) {
			stats.addToKey("PMCID");
		}
		IResult r = provider.getDocument(lox, credentials);
		if (r.getResultObject() != null) {
			environment.logDebug("JSONDocumentReader.processJSON already exists "+lox);
			//TODO must see if that's an abstract already
		} else {
			//Then just save it
			environment.logDebug("JSONDocumentReader.processJSON-1 "+doc.toJSONString());
			r = provider.putDocument(doc);
			stats.addToKey(IASRFields.DOCS_IMPORTED);
		}
	}
}
