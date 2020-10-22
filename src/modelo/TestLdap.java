package modelo;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;

/*
 * esta clase implementa a ldapAuth para probar el funcionamiento
 * 
 * 
 * 
 * */
public class TestLdap {

	public static void main(String[] args) throws NamingException{
        System.out.println("Iniciando Autenticacion");
         
        String server="ldap://127.0.0.1:10389"; // servidor de LDAP
        String usuario="admin"; // Usuario de Autenticacion
        String dn="uid=" + usuario + ",ou=system,dc=example,dc=com"; // Ruta del Arbol LDAP
        String tipoAuth="simple";//tipo de autentuicacion simple o por SSL
        String clave="secret";
 
        LdapAuth ldapAuth=new LdapAuth(server,dn,tipoAuth,usuario,clave);
 
        if(ldapAuth.isAutenticado()){
            System.out.println("Usuario "+ldapAuth.getUsuario()+" Autenticado Correctamente");
            
            /* obtenemos una Propiedad de la autenticacion
             *
             * Algunas Propiedades Disponibles
             * mailLocalAddress,displayName,givenName,objectClass,userPassword,sambaLogonTime,mail
             * uid,uidNumber,cn,loginShell,gidNumber,gecos,sambaSID,homeDirectory
             */
           /* Attribute atr=ldapAuth.cargarPropiedadConexion("mail");
            System.out.println("1. Atributo "+atr.getID());
            System.out.println("1. Valor "+atr.get().toString());*/
        }
        else{
            System.out.println("Usuario "+ldapAuth.getUsuario()+" No se Puedo Autenticar");
        }
    }

}
