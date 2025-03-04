/**********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Diego Ruiz - BX Service GmbH                                      *
 **********************************************************************/
package de.bxservice.model.docstatusvalidator;

import java.util.List;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventManager;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.ServerStateChangeEvent;
import org.compiere.model.ServerStateChangeListener;
import org.compiere.model.X_AD_Table_ScriptValidator;
import org.compiere.util.Util;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;


@Component(
		reference = @Reference( 
				name = "IEventManager", bind = "bindEventManager", unbind="unbindEventManager", 
				policy = ReferencePolicy.STATIC, cardinality =ReferenceCardinality.MANDATORY, service = IEventManager.class)
		)
public class DocumentStatusValidator extends AbstractEventHandler {

	@Override
	protected void initialize() {
		if (!Adempiere.isStarted()) {
			Adempiere.addServerStateChangeListener(new ServerStateChangeListener() {
				@Override
				public void stateChange(ServerStateChangeEvent event) {
					if (event.getEventType() == ServerStateChangeEvent.SERVER_START && Adempiere.isStarted())
							initialize();
				}
			});
			return;
		}

		List<MTable> docStatusTables = MBXSDocValidation.getDocumentStatusTables();

		for (MTable table : docStatusTables) {
			String tableName = table.getTableName();
			registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_VOID, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_CLOSE, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_REACTIVATE, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_REVERSECORRECT, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_REVERSEACCRUAL, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_VOID, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_CLOSE, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_REACTIVATE, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_REVERSECORRECT, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_REVERSEACCRUAL, tableName);
			registerTableEvent(IEventTopics.DOC_BEFORE_POST, tableName);
			registerTableEvent(IEventTopics.DOC_AFTER_POST, tableName);
		}
	} //initialize

	@Override
	protected void doHandleEvent(Event event) {
		String type = event.getTopic();
		PO po = getPO(event);
		
		List<MBXSDocValidation> docValidators = MBXSDocValidation.getDocumentValidator(po.get_Table_ID(), po.getAD_Client_ID(), getEventValidator(type));
		
		StringBuilder errorMessage = new StringBuilder();
		for (MBXSDocValidation docValidator : docValidators) {
			if (docValidator.isRaiseAlert(po)) {
				errorMessage.append("<br>"  + docValidator.get_Translation("Message"));
			}
		}
		
		if (!Util.isEmpty(errorMessage.toString(), true))
			throw new AdempiereException(errorMessage.toString());
		
	} //doHandleEvent

	private String getEventValidator(String eventType) {
		switch(eventType) {
		case IEventTopics.DOC_BEFORE_PREPARE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforePrepare;
		case IEventTopics.DOC_BEFORE_VOID:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforeVoid;
		case IEventTopics.DOC_BEFORE_CLOSE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforeClose;
		case IEventTopics.DOC_BEFORE_REACTIVATE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforeReactivate;
		case IEventTopics.DOC_BEFORE_REVERSECORRECT:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforeReverseCorrect;
		case IEventTopics.DOC_BEFORE_REVERSEACCRUAL:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforeReverseAccrual;
		case IEventTopics.DOC_BEFORE_COMPLETE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforeComplete;
		case IEventTopics.DOC_AFTER_PREPARE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterPrepare;
		case IEventTopics.DOC_AFTER_COMPLETE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterComplete;
		case IEventTopics.DOC_AFTER_VOID:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterVoid;
		case IEventTopics.DOC_AFTER_CLOSE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterClose;
		case IEventTopics.DOC_AFTER_REACTIVATE:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterReactivate;
		case IEventTopics.DOC_AFTER_REVERSECORRECT:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterReverseCorrect;
		case IEventTopics.DOC_AFTER_REVERSEACCRUAL:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterReverseAccrual;
		case IEventTopics.DOC_BEFORE_POST:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentBeforePost;
		case IEventTopics.DOC_AFTER_POST:
			return X_AD_Table_ScriptValidator.EVENTMODELVALIDATOR_DocumentAfterPost;
		default:
			return null;
		}
	}

}
