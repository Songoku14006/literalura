package com.alura.literalura.Principal;

import com.alura.literalura.models.DadosLivro;
import com.alura.literalura.models.Livro;
import com.alura.literalura.models.RespostaApi;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;

import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books";
    private final String BUSCA = "?search=";
    private LivroRepository repositorio;
    private List<Livro> livros = new ArrayList<>();

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                     1 - Buscar livro pelo título
                     2 - Listar livros Registrados
                     3 - Listar autores registrados
                     4 - Listar autores vivos em um determinado ano
                     5 - Listar livros em um determinado idioma
                                    \s
                     0 - Sair                                \s
                    \s""";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;
                case 0:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
        var link = ENDERECO + BUSCA + nomeLivro.replace(" ", "%20");
        System.out.println(link);

        var json = consumo.obterDados(link);
        System.out.println("JSON recebido: " + json);

        RespostaApi respostaApi = conversor.obterDados(json, RespostaApi.class);

        if (respostaApi == null || respostaApi.getResults().isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
            return null;
        }

        DadosLivro dadosLivro = respostaApi.getResults().get(0);
        System.out.println("Primeiro livro encontrado: " + dadosLivro);

        return dadosLivro;
    }


    private void buscarLivroPorTitulo() {
        DadosLivro dados = getDadosLivro();
        if (dados == null) {
            System.out.println("Nenhum dado foi retornado para salvar.");
            return;
        }

        Livro livro = new Livro(dados);
        repositorio.save(livro);
        System.out.println("Livro salvo: " + livro.getTitulo());
    }

    private void listarLivrosRegistrados() {
        livros = repositorio.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<String> listarAutores = repositorio.listAutors();
        System.out.println(listarAutores);
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Digite um ano");
        var anoNascimento = leitura.nextInt();
        List<String> listarAutoresVivos = repositorio.listAutorsAliveInEspecificYear(anoNascimento);
        System.out.println(listarAutoresVivos);
    }

    private void listarLivrosEmDeterminadoIdioma() {
        var idiomas = repositorio.listLanguage();
        System.out.println("Idiomas presentes no banco de dados" +
                idiomas);
        System.out.println("Digite um idioma");
        var idiomaPesquisa = leitura.nextLine();
        List<String> listBooksByLanguage = repositorio.listBooksInEspecificLanguage(idiomaPesquisa);
        System.out.println(listBooksByLanguage);
    }
}

