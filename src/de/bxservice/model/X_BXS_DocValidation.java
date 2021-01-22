/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package de.bxservice.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for BXS_DocValidation
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_BXS_DocValidation extends PO implements I_BXS_DocValidation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210122L;

    /** Standard Constructor */
    public X_BXS_DocValidation (Properties ctx, int BXS_DocValidation_ID, String trxName)
    {
      super (ctx, BXS_DocValidation_ID, trxName);
      /** if (BXS_DocValidation_ID == 0)
        {
			setAD_Table_ID (0);
			setBXS_DocValidation_ID (0);
			setDescription (null);
			setEventModelValidator (null);
			setName (null);
			setWhereClause (null);
        } */
    }

    /** Load Constructor */
    public X_BXS_DocValidation (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_BXS_DocValidation[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_Value (COLUMNNAME_AD_Table_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Document Status Validation.
		@param BXS_DocValidation_ID Document Status Validation	  */
	public void setBXS_DocValidation_ID (int BXS_DocValidation_ID)
	{
		if (BXS_DocValidation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BXS_DocValidation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BXS_DocValidation_ID, Integer.valueOf(BXS_DocValidation_ID));
	}

	/** Get Document Status Validation.
		@return Document Status Validation	  */
	public int getBXS_DocValidation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_DocValidation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BXS_DocValidation_UU.
		@param BXS_DocValidation_UU BXS_DocValidation_UU	  */
	public void setBXS_DocValidation_UU (String BXS_DocValidation_UU)
	{
		set_Value (COLUMNNAME_BXS_DocValidation_UU, BXS_DocValidation_UU);
	}

	/** Get BXS_DocValidation_UU.
		@return BXS_DocValidation_UU	  */
	public String getBXS_DocValidation_UU () 
	{
		return (String)get_Value(COLUMNNAME_BXS_DocValidation_UU);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EventModelValidator AD_Reference_ID=53237 */
	public static final int EVENTMODELVALIDATOR_AD_Reference_ID=53237;
	/** Table Before New = TBN */
	public static final String EVENTMODELVALIDATOR_TableBeforeNew = "TBN";
	/** Table Before Change = TBC */
	public static final String EVENTMODELVALIDATOR_TableBeforeChange = "TBC";
	/** Table Before Delete = TBD */
	public static final String EVENTMODELVALIDATOR_TableBeforeDelete = "TBD";
	/** Table After New = TAN */
	public static final String EVENTMODELVALIDATOR_TableAfterNew = "TAN";
	/** Table After Change = TAC */
	public static final String EVENTMODELVALIDATOR_TableAfterChange = "TAC";
	/** Table After Delete = TAD */
	public static final String EVENTMODELVALIDATOR_TableAfterDelete = "TAD";
	/** Document Before Prepare = DBPR */
	public static final String EVENTMODELVALIDATOR_DocumentBeforePrepare = "DBPR";
	/** Document Before Void = DBVO */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeVoid = "DBVO";
	/** Document Before Close = DBCL */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeClose = "DBCL";
	/** Document Before Reactivate = DBAC */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeReactivate = "DBAC";
	/** Document Before Reverse Correct = DBRC */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeReverseCorrect = "DBRC";
	/** Document Before Reverse Accrual = DBRA */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeReverseAccrual = "DBRA";
	/** Document Before Complete = DBCO */
	public static final String EVENTMODELVALIDATOR_DocumentBeforeComplete = "DBCO";
	/** Document Before Post = DBPO */
	public static final String EVENTMODELVALIDATOR_DocumentBeforePost = "DBPO";
	/** Document After Prepare = DAPR */
	public static final String EVENTMODELVALIDATOR_DocumentAfterPrepare = "DAPR";
	/** Document After Void = DAVO */
	public static final String EVENTMODELVALIDATOR_DocumentAfterVoid = "DAVO";
	/** Document After Close = DACL */
	public static final String EVENTMODELVALIDATOR_DocumentAfterClose = "DACL";
	/** Document After Reactivate = DAAC */
	public static final String EVENTMODELVALIDATOR_DocumentAfterReactivate = "DAAC";
	/** Document After Reverse Correct = DARC */
	public static final String EVENTMODELVALIDATOR_DocumentAfterReverseCorrect = "DARC";
	/** Document After Reverse Accrual = DARA */
	public static final String EVENTMODELVALIDATOR_DocumentAfterReverseAccrual = "DARA";
	/** Document After Complete = DACO */
	public static final String EVENTMODELVALIDATOR_DocumentAfterComplete = "DACO";
	/** Document After Post = DAPO */
	public static final String EVENTMODELVALIDATOR_DocumentAfterPost = "DAPO";
	/** Table After New Replication = TANR */
	public static final String EVENTMODELVALIDATOR_TableAfterNewReplication = "TANR";
	/** Table After Change Replication = TACR */
	public static final String EVENTMODELVALIDATOR_TableAfterChangeReplication = "TACR";
	/** Table Before Delete Replication = TBDR */
	public static final String EVENTMODELVALIDATOR_TableBeforeDeleteReplication = "TBDR";
	/** Set Event Model Validator.
		@param EventModelValidator Event Model Validator	  */
	public void setEventModelValidator (String EventModelValidator)
	{

		set_Value (COLUMNNAME_EventModelValidator, EventModelValidator);
	}

	/** Get Event Model Validator.
		@return Event Model Validator	  */
	public String getEventModelValidator () 
	{
		return (String)get_Value(COLUMNNAME_EventModelValidator);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sql WHERE.
		@param WhereClause 
		Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause)
	{
		set_Value (COLUMNNAME_WhereClause, WhereClause);
	}

	/** Get Sql WHERE.
		@return Fully qualified SQL WHERE clause
	  */
	public String getWhereClause () 
	{
		return (String)get_Value(COLUMNNAME_WhereClause);
	}
}