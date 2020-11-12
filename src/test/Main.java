package test;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/*
 * esta clase es para probar que nos podemos conectar a nuestro LDAP y que adem�s podemos a�adir atributos
 * Ahora hay que conseguir que los datos sean entrados a trav�s de la aplicaci�n
 * /*
 */
public class Main {

    public static void main (String[] args){
        System.out.println("Iniciando Autenticacion");
        try
		  {
			  Hashtable<String, String> ldapEnv = new Hashtable<>();
			  ldapEnv.put( Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			  ldapEnv.put(Context.PROVIDER_URL, "ldap://localhost:10389");
			  ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			  ldapEnv.put(Context.SECURITY_PRINCIPAL , "uid=admin,ou= system");
			  ldapEnv.put(Context.SECURITY_CREDENTIALS, "secret");
			  
			  // si queremos a�adir un usuario se haria as�.
			  DirContext context = new InitialDirContext(ldapEnv);
			  
			  Attributes attributes =new BasicAttributes();
			  Attribute attribute =new BasicAttribute("objectClass");
			  attribute.add("inetOrgPerson");
			  attributes.put(attribute);
			  Attribute sn =new BasicAttribute("sn");
			  sn.add("Karthik");
			  Attribute cn =new BasicAttribute("cn");
			  cn.add("Raja");
			  
			  attributes.put(sn);
			  attributes.put(cn);
			  attributes.put("telephoneNumber", "12332");
			  context.createSubcontext("employeeNumber=1 ,ou=users,ou=system",attributes);
			  
			  System.out.println(" success");
		 }
		  catch (Exception e) {
			// TODO: handle exception
		}
	  }
}
