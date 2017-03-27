package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface CommandeProduitsDao {

	@SqlUpdate("create table commandesProduits(idc integer primary key,"
			+ "idp integer primary key,"
			+ "nombre integer,"
			+ "Constraint fk_produits foreign key(idp) references produits(idp),"
			+ "Constraint fk_commandes foreign key(idc) references commandes(idc))")
	void createCommandeProduitsTable();
	
    @SqlUpdate("insert into commandesProduits(idc, idp,nombre) values"
    		+ " (:idc, :idp, :nombre)")
    @GetGeneratedKeys
    int insert(@BindBean() CommandeProduits commandeP);

    @SqlQuery("select * from commandesProduits where idc = :idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    CommandeProduits findByIdc(@Bind("idc") Integer idc);

    @SqlQuery("select * from commandesProduits where search like :idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<CommandeProduits> search(@Bind("idc") Integer idc);

    @SqlUpdate("drop table if exists commandesProduits")
    void dropCommandeTable();

    @SqlUpdate("delete from commandesProduits where idc = :idc")
    void delete(@Bind("idc") int idc);

    @SqlQuery("select * from commandesProduits order by idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<CommandeProduits> all();

    @SqlQuery("select * from commandesProduits where idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
    CommandeProduits findByIdp(@Bind("idp") int idp);
    

    
    @SqlUpdate("update commandesProduits set idc =:idc , idp =:idp, nombre = :nombre ,where idp = :idp and idc = :idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    CommandeProduits updateCommandes(@Bind("idc") int idc);

    void close();
}
