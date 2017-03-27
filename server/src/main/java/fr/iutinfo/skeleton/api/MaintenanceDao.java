package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface MaintenanceDao {
	@SqlUpdate("create table Maintenance(idM integer primary key autoincrement,idUser integer,"
			+ "idPro integer, date text,type text, rapport text,"
			+ " Constraint fk_Maintenance1 foreign key(idUser) references users(id),"
			+ " Constraint fk_Maintenance2 foreign key(idPro) references produits(idp))")
	void createMaintenanceTable();
	
    @SqlUpdate("insert into Maintenance (idPro,idUser ,date, type, rapport) values"
    		+ " (:idPro, :idUser, :date, :type, :rapport)")
    @GetGeneratedKeys
    int insert(@BindBean() Maintenance Maintenance);

    @SqlQuery("select * from Maintenance where idM = :idM")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Maintenance findByidM(@Bind("idM") int idM);
    
    @SqlQuery("select * from Maintenance where idUser = :idUser")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Maintenance findByidUser(@Bind("idUser") int idUser);

    @SqlQuery("select * from Maintenance where idPro = :idPro")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Maintenance findByIdPro(@Bind("idPro") int idPro);
    
    @SqlUpdate("drop table if exists Maintenance")
    void dropMaintenanceTable();

    @SqlUpdate("delete from Maintenance where idM = :idM")
    void delete(@Bind("idM") int idM);

    @SqlQuery("select * from Maintenance order by date")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Maintenance> all();
    
    @SqlQuery("update Maintenance set "
    		+ "idPro=:idPro ,date=:date, type=:type, effectue=:effectue,rapport=:rapport"
    		+ " where idM = :idM")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Maintenance updateMaintenance(@Bind("idM") int idM);
    

}
