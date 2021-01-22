package de.bxservice.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MBXSDocValidation extends X_BXS_DocValidation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4801859772142134002L;
	
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
	
	public static List<MBXSDocValidation> getDocumentValidator(int AD_Table_ID, int AD_Client_ID, String EventValidator) {
		final String whereClause = "AD_Client_ID IN (0,?) AND AD_Table_ID = ? AND EventModelValidator=?";
		List <MBXSDocValidation> documentValidators = new Query(Env.getCtx(), Table_Name, whereClause, null)
				.setParameters(AD_Client_ID, AD_Table_ID, EventValidator)
				.setOnlyActiveRecords(true)
				.setOrderBy("SeqNo")
				.list();
		
		return documentValidators;
	}

	public static List<MTable> getDocumentStatusTables() {
		final String whereClause = 
				"IsView = 'N' AND isActive='Y' AND EXISTS (SELECT * FROM ad_column ac WHERE ac.columnname = 'DocStatus' AND ac.ad_table_id=AD_Table.ad_table_id)";
		List <MTable> documentValidators = new Query(Env.getCtx(), MTable.Table_Name, whereClause, null)
				.setOnlyActiveRecords(true)
				.list();
	
		return documentValidators;
	}
	
	public boolean isRaiseAlert(PO po) {
		final String whereClause = Env.parseContext(Env.getCtx(), -1, getWhereClause(), false, true) 
				+ " AND " + po.get_KeyColumns()[0] + " = ?";

		final String sql = "SELECT 1 FROM " + po.get_TableName() + " WHERE " + whereClause;
		return DB.getSQLValueEx(get_TrxName(), sql, po.get_ID()) > 0;
	}

}
