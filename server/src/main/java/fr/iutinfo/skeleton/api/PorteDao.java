package fr.iutinfo.skeleton.api;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

public interface PorteDao {
	@SqlUpdate("create table porte(id integer primary key autoincrement, idUser integer, description varchar(1024), dateLastMaintenance varchar(64), descLastMaintenance varchar(1024),"
			+ "Constraint FK_Porte1 foreign key(idUser) references users(id))")
	void createPorteTable();

	@SqlUpdate("insert into Porte (idUser, description, dateLastMaintenance, descLastMaintenance) values"
			+ " (:idUser, :description, :dateLastMaintenance, :descLastMaintenance)")
	@GetGeneratedKeys
	int insert(@BindBean() Porte door);

	@SqlQuery("select * from porte where id = :id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Porte findByid(@Bind("id") int id);

	@SqlQuery("select * from porte where idUser = :idUser")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Porte> findByidUser(@Bind("idUser") int idUser);

	@SqlUpdate("drop table if exists porte")
	void dropPorteTable();

	@SqlUpdate("delete from Porte where id = :id")
	void delete(@Bind("id") int id);

	@SqlQuery("select * from porte order by id")
	@RegisterMapperFactory(BeanMapperFactory.class)
	List<Porte> all();

	@SqlUpdate("update Porte set " + "idUser=:idUser ,description=:description, dateLastMaintenance=:dateLastMaintenance, descLastMaintenance=:descLastMaintenance"
			+ " where id = :id")
	Porte updatePorte(@BindBean() Porte door);

}
