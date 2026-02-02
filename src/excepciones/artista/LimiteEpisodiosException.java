package excepciones.artista;

public class LimiteEpisodiosException extends Exception{
    public LimiteEpisodiosException(){
        super("Limite de episodios alcanzado.");
    }
    public LimiteEpisodiosException(String mensaje){
        super(mensaje);
    }
}
