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

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MBXSDocValidation extends X_BXS_DocValidation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4801859772142134002L;
	private static final CCache<String, List<MBXSDocValidation>> s_DocValitorCache = new CCache<>(null, "MBXSDocValidation", 30, 120, false, 2000);

	@Override
	protected boolean beforeSave(boolean newRecord) {
		if (getSeqNo() == 0) {
			final String sql = "SELECT COALESCE(MAX(SeqNo),0) + 10 FROM "+ Table_Name
								+" WHERE AD_Table_ID=? AND EventModelValidator=?";
			int seqNo = DB.getSQLValueEx(get_TrxName(), sql, getAD_Table_ID(), getEventModelValidator());
			setSeqNo(seqNo);
		}
		return true;
	}

	public MBXSDocValidation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public MBXSDocValidation(Properties ctx, int BXS_DocValidation_ID, String trxName) {
		super(ctx, BXS_DocValidation_ID, trxName);
	}
	
	public static List<MBXSDocValidation> getDocumentValidator(int AD_Table_ID, int AD_Client_ID, String eventValidator) {
		String key = AD_Table_ID + "|" + AD_Client_ID + "|" + eventValidator;  
		List<MBXSDocValidation> cache = s_DocValitorCache.get(key);
		if (cache != null)
			return cache;
		
		final String whereClause = "AD_Client_ID IN (0,?) AND AD_Table_ID = ? AND EventModelValidator=?";
		List <MBXSDocValidation> documentValidators = new Query(Env.getCtx(), Table_Name, whereClause, null)
				.setParameters(AD_Client_ID, AD_Table_ID, eventValidator)
				.setOnlyActiveRecords(true)
				.setOrderBy("SeqNo")
				.list();
		
		s_DocValitorCache.put(key, documentValidators);
		return documentValidators;
	}

	public static List<MTable> getDocumentStatusTables() {
		final String whereClause = 
				"IsView = 'N' AND EXISTS (SELECT * FROM ad_column ac WHERE ac.columnname = 'DocStatus' AND ac.ad_table_id=AD_Table.ad_table_id AND ac.IsActive='Y')";
		List <MTable> documentValidators = new Query(Env.getCtx(), MTable.Table_Name, whereClause, null)
				.setOnlyActiveRecords(true)
				.list();
	
		return documentValidators;
	}
	
	public boolean isRaiseAlert(PO po) {
		final String whereClause = Env.parseVariable(getWhereClause(), po, null,  false) 
				+ " AND " + po.get_KeyColumns()[0] + " = ?";

		final String sql = "SELECT 1 FROM " + po.get_TableName() + " WHERE " + whereClause;
		return DB.getSQLValueEx(po.get_TrxName(), sql, po.get_ID()) > 0;
	}

}
