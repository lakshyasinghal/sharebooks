//package com.sharebooks.dao.sql;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//
//import com.sharebooks.coreEntities.Proposal;
//import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
//import com.sharebooks.database.sql.Database;
//import com.sharebooks.database.sql.SqlInsertQueryProcessor;
//import com.sharebooks.database.sql.SqlUpdateQueryProcessor;
//import com.sharebooks.database.sql.Table;
//import com.sharebooks.database.sql.customQueries.ProposalQueries;
//import com.sharebooks.database.sql.query.SqlInsertQuery;
//import com.sharebooks.database.sql.query.SqlQuery;
//import com.sharebooks.database.sql.query.SqlUpdateQuery;
//
//public class ProposalSqlDao {
//	private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class.getName());
//	private final Database database = Database.SHAREBOOKS;
//	private final ProposalQueries proposalQueries = ProposalQueries.instance();
//	
//	@Override
//	public boolean createProposal(Proposal proposal) throws Exception {
//		LOGGER.trace("Entering createProposal");
//		//get proposalMap map 
//		Map<String,Object> proposalMap = proposal.map();
//		//remove id field and id value from map as it won't be required in insert query
//		proposalMap.remove("id");
//		SqlQuery query = new SqlInsertQuery(Table.Proposals.desc(), proposalMap);
//		query.build();
//		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
//		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
//		LOGGER.info("Rows Affected:"+rowsAffected);
//		LOGGER.trace("Leaving createProposal");
//		return rowsAffected>0?true:false;
//	}
//
//	@Override
//	public boolean updateProposal(String proposalUid, String bookUid, int status) throws Exception {
//		LOGGER.trace("Entering updateProposal");
//		Map<String,Object> proposalMap = new HashMap<String,Object>();
//		proposalMap.put("uid", proposalUid);
//		proposalMap.put("bookUid", bookUid);
//		proposalMap.put("status", status);
//		
//		SqlQuery query = new SqlUpdateQuery(Table.Proposals.desc(), proposalMap);
//		query.build();
//		LOGGER.debug(query.toString());
//		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
//		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
//		LOGGER.trace("Leaving updateProposal");
//		return rowsAffected>0?true:false;
//	}
//}
