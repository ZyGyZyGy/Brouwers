package be.vdab.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Adres;

@Repository
public class JdbcBrouwerRepository implements BrouwerRepository {

    private static final String BEGIN_SQL = 
	    "select id, naam, omzet, straat, huisNr, postcode, gemeente "
	  + "from brouwers ";
    private static final String SQL_FIND_ALL = BEGIN_SQL + "order by naam";
    private static final String SQL_FIND_BY_NAAM = BEGIN_SQL + 
	    "where naam like ? "
	  + "order by naam";
    
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    
    private final RowMapper<Brouwer> rowMapper = (resultSet, rowNum) ->
    	new Brouwer(
    		resultSet.getLong("id"),
    		resultSet.getString("naam"),
    		(Integer) resultSet.getInt("omzet"),
    		new Adres(
    			resultSet.getString("straat"),
    			resultSet.getString("huisNr"),
    			resultSet.getInt("postcode"),
    			resultSet.getString("gemeente")));
    
    JdbcBrouwerRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedJdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
	this.namedJdbcTemplate = namedJdbcTemplate;
	this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
	simpleJdbcInsert.withTableName("brouwers");
	simpleJdbcInsert.usingGeneratedKeyColumns("id");
    }
    
    @Override
    public void create(Brouwer brouwer) {
	Map<String, Object> kolomWaarden = new HashMap<>();
	kolomWaarden.put("naam", brouwer.getNaam());
	kolomWaarden.put("omzet", brouwer.getOmzet());
	kolomWaarden.put("straat", brouwer.getAdres().getStraat());
	kolomWaarden.put("huisNr", brouwer.getAdres().getHuisNr());
	kolomWaarden.put("postcode", brouwer.getAdres().getPostcode());
	kolomWaarden.put("gemeente", brouwer.getAdres().getGemeente());
	simpleJdbcInsert.execute(kolomWaarden);
    }

    @Override
    public List<Brouwer> findAll() {
	return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
    }

    @Override
    public List<Brouwer> findByNaam(String beginNaam) {
	return jdbcTemplate.query(SQL_FIND_BY_NAAM, rowMapper, beginNaam + "%");
    }

    @Override
    public List<Brouwer> findByNaam(char eersteLetter) {
	return jdbcTemplate.query(SQL_FIND_BY_NAAM, rowMapper, eersteLetter + "%");
    }

}






