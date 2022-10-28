package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final FuncionarioRepository funcionarioRepository;
    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual ação de funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario nome");
            System.out.println("2 - Busca funcionario nome salario maior data nome");
            System.out.println("3 - Busca funcionario data contratacao");
            System.out.println("3 - Busca funcionario salario");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    pesquisaFuncionarioSalario();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner) {

        System.out.print("\nQual nome deseja pesquisar ?");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);

    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.print("\nQual nome deseja pesquisar ?");
        String nome = scanner.next();

        System.out.print("\nQual data da contratacao deseja pesquisar ?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.print("\nQual salario deseja pesquisar ?");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
        list.forEach(System.out::println);

    }

    private void buscaFuncionarioDataContratacao (Scanner scanner) {

        System.out.print("\nQual data da contratacao deseja pesquisar ?");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
        list.forEach(System.out::println);

    }

    private void pesquisaFuncionarioSalario() {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario | id: " + f.getId() + " | nome: "
                + f.getNome() + " | salario: " + f.getSalario()));
    }

}
