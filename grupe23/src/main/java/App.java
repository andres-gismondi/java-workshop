import dao.*;
import model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        //se crea categoria
        Category c = new Category();
        c.setName("Teacher");
        c.setWritePermisson(true);
        //se crea categoria
        Category cc = new Category();
        cc.setName("Teacher");
        cc.setWritePermisson(true);

        CategoryDAO categoryDAO = DaoFactory.getCategoryDAO();
        categoryDAO.persistir(c);
        categoryDAO.persistir(cc);


        //se crea a un usuario
        User p = new Admin();
        p.setFirstName("Andy");
        p.setLastName("Kato");
        p.setEmail("bruceLee@gmail.com");
        p.setType("User");
        p.addCategory(c);
        p.addCategory(cc);

        //se le pide a daoFactory un usuarioDAO
        UserDAO user = DaoFactory.getUserDAO();
        user.persistir(p);


        //crear comentario
        Commentary comentario = new Commentary();
        comentario.setBody("primer comentario");
        comentario.setCreator(p);
        comentario.setDate(new Timestamp(System.currentTimeMillis()));
        comentario.setTitle("Coment");
        //crear segundo comentario
        Commentary comentario2 = new Commentary();
        comentario2.setBody("primer comentario");
        comentario2.setCreator(p);
        comentario2.setDate(new Timestamp(System.currentTimeMillis()));
        comentario2.setTitle("Coment");

        CommentaryDAO commentaryDAO = DaoFactory.getCommentaryDao();
        commentaryDAO.persistir(comentario);
        commentaryDAO.persistir(comentario2);

        //crear publicacion
        Publication publicacion = new Publication();
        publicacion.setBody("el body de una publicacion");
        publicacion.setCreator(p);
        publicacion.setDate(new Timestamp(System.currentTimeMillis()));
        publicacion.setEnableComments(true);
        publicacion.addComentary(comentario);
        publicacion.addComentary(comentario2);

        //Publicacion DAO
        PublicationDAO publicationDAO = DaoFactory.getPublicationDAO();
        publicationDAO.persistir(publicacion);



        //se borra un usuario. este no anda, hay que consultar.
      /* user.borrar(10);

        //actualizando usuario
        User actualizar = user.recuperar(1);
        actualizar.setEmail("pepito@pepas.com");
        user.actualizar(actualizar);*/

    }
}


