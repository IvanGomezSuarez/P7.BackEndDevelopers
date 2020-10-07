package controlador;

import java.io.IOException;

import javax.naming.directory.DirContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ldapAuth;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	
	private String usuario;
    private String clave;
    private String servidor;
    private String dn;
    private String tipoAuth;
    private boolean autenticado;
     
    DirContext dc;
	private static final long serialVersionUID = 1L;
       
	
	public Servlet(String server, String dn, String ta,String usuario,String clave) {
        this.servidor = server;
        this.dn = dn;
        this.tipoAuth = ta;
        this.usuario=usuario;
        this.clave=clave;
        inicializarConexion();
    }
	
	public void inicializarConexion() {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, servidor);
        env.put(Context.SECURITY_AUTHENTICATION, tipoAuth);
        env.put(Context.SECURITY_PRINCIPAL, dn);
        env.put(Context.SECURITY_CREDENTIALS, clave);
 
        try {
            dc = new InitialDirContext(env);
            setAutenticado(true);
        } catch (NamingException ex) {
            System.out.println("Error Autenticando mediante LDAP, Error causado por : " + ex.toString());
            setAutenticado(false);
        }
    }
	
	/*Get's y Set's*/
    public boolean isAutenticado() {
        return autenticado;
    }
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * METHOD TO HANDLE FORM POST
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String usuario	=	request.getParameter("txtNombre");
		String clave	=	request.getParameter("psw");
		String server="ldap://127.0.0.1:389"; // servidor de LDAP
        String dn="uid=" + usuario + ",ou=system,dc=example,dc=com"; // Ruta del Arbol LDAP
        String tipoAth="simple";//tipo de autentuicacion simple o por SSL	

		ldapAuth ldapAuth=new ldapAuth(server,dn,tipoAth,usuario,clave);
		 
        if(ldapAuth.isAutenticado()){
            System.out.println("Usuario "+ldapAuth.getUsuario()+" Autenticado Correctamente");
            response.sendRedirect("Principal.jsp");
            
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

