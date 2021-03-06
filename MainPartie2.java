public class MainPartie2 {

	private static final int NB_MAX = 30 ;  
	public static void main(String[] args) {
		
		Client [] clients = new Client [NB_MAX];
		Plat []   plats = new Plat [NB_MAX];
		Commande [] commandes = new Commande [NB_MAX];
	
	}
	
	
	// get le prix du plan dans la list des plats
	public static double getPrixduPlat(String nomPlat, Plat[] pplats) 
	{
		double prix = 0.0;
		for (int i=0;i<pplats.length  && pplats[i] !=null ;i++) 
		{
			if(pplats[i].getNomPlat().equals(nomPlat) )
			{
				prix = pplats[i].getPrix() ;
				break;
			}
		}
		return prix;
	}
			// get les clients a partir du fichier text
	public static Client [] liresClients (byte []bytes){
		Client [] clients = new Client [NB_MAX];
		try{
			String text = new String (bytes);
			String cli = text.substring(text.indexOf("Clients :"),text.lastIndexOf("Plats :"));
		
			String  [] listesClients = cli.split("\r\n");
			
			for(int i=1; i<listesClients.length ;i++){
			
				clients[i-1] =new Client(listesClients[i]);
			}
		
		}catch(Exception e){
			System.out.print(e.toString());
		}
		return clients;
	}
		// get Plats du fichier text
	public static Plat []  liresPlats (byte []bytes){
		
		Plat [] platsList = new Plat [NB_MAX];
		
		try{
			String text = new String (bytes);
			String plats = text.substring(text.indexOf("Plats :"),text.lastIndexOf("Commandes :"));
			String  [] listesPlats = plats.split("\r\n");
			for(int i=1; i<listesPlats.length ;i++){
				String plat = listesPlats[i].split(" ")[0] ;
				String prix = listesPlats[i].split(" ")[1] ;
				Plat p = new Plat(plat, (Double.parseDouble(prix)) ) ;
				platsList[i-1] = p; 		
			}
		
		}catch(Exception e){
			System.out.print(e.toString());
		}
		return platsList;
	}
	
	// get les commandes du fichier text 
	public static Commande []  liresCommandes (byte []bytes){
		
		Commande [] CommandesList = new Commande [NB_MAX];
		
		try{
			String text = new String (bytes);
			String com = text.substring(text.indexOf("Commandes :"),text.lastIndexOf("Fin"));
			String  [] listesCommandes = com.split("\r\n");
			
			for(int i=1; i<listesCommandes.length ;i++){
				
				String cli  = listesCommandes[i].split(" ")[0] ;
				String nomPlat = listesCommandes[i].split(" ")[1] ;
				String qte  = listesCommandes[i].split(" ")[2] ;
		
				Client cliobj = new Client(cli) ;
			    Commande cmd = new Commande (cliobj,nomPlat, Integer.parseInt(qte)) ; 
			    
			    CommandesList[i-1] = cmd; 		
				
			}
		
		}catch(Exception e){

			return null; 
		}
		return CommandesList;
		
		}
	
}
