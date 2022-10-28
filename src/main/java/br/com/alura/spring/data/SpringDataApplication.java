package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCargoService cargoService;
    private final CrudFuncionarioService funcionarioService;
    private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;
    private final RelatoriosService service;
    private Boolean system = true;

    public SpringDataApplication(CrudCargoService cargoService,
                                 CrudFuncionarioService funcionarioService,
                                 CrudUnidadeTrabalhoService unidadeTrabalhoService,
                                 RelatorioFuncionarioDinamico relatorioFuncionarioDinamico,
                                 RelatoriosService service) {
        this.cargoService = cargoService;
        this.funcionarioService = funcionarioService;
        this.unidadeTrabalhoService = unidadeTrabalhoService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionario");
            System.out.println("3 - Unidade de Trabalho");
            System.out.println("4 - Relatorios");
            System.out.println("5 - Relatorio dinamico");

            int action = scanner.nextInt();

            switch (action) {
                case 0:
                    system = false;
                    break;
                case 1:
                    cargoService.inicial(scanner);
                    break;
                case 2:
                    funcionarioService.inicial(scanner);
                    break;
                case 3:
                    unidadeTrabalhoService.inicial(scanner);
                    break;
                case 4:
                    service.inicial(scanner);
                    break;
                case 5:
                    relatorioFuncionarioDinamico.inicial(scanner);
                    break;
                default:
                    system = false;
            }
        }
    }
}
