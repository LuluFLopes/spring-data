package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private Boolean system = true;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual ação de funcionario deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Excluir");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void salvar(Scanner scanner) {

        System.out.print("\nNome: ");
        String nome = scanner.next();

        System.out.print("\nCpf: ");
        String cpf = scanner.next();

        System.out.print("\nSalario: ");
        Double salario = scanner.nextDouble();

        System.out.print("\nData de contratação: ");
        String dataBruta = scanner.next();
        DateFormat data = DateFormat.getDateInstance();
        Date dataFormatada = null;
        try {
            dataFormatada = data.parse(dataBruta);
        } catch (ParseException e) {
            System.out.println("Não foi possível converter a data.");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(dataFormatada.toInstant().atZone(ZoneId
                .systemDefault()).toLocalDate());
        funcionarioRepository.save(funcionario);
        System.out.println("\nSalvo");
    }

    private void atualizar(Scanner scanner) {

        System.out.print("Digite o id do cadastro a ser atualizado: ");
        int id = scanner.nextInt();

        System.out.print("\nNovo nome: ");
        String novoNome = scanner.next();

        System.out.print("\nNovo cpf: ");
        String novoCpf = scanner.next();

        System.out.print("\nNovo salario: ");
        Double novoSalario = scanner.nextDouble();

        System.out.print("\nNova data: ");
        String novaData = scanner.next();
        DateFormat data = DateFormat.getDateInstance();
        Date dataFormatada = null;
        try {
            dataFormatada = data.parse(novaData);
        } catch (ParseException e) {
            System.out.println("Não foi possível converter a data.");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(novoNome);
        funcionario.setCpf(novoCpf);
        funcionario.setSalario(novoSalario);
        funcionario.setDataContratacao(dataFormatada.toInstant().atZone(ZoneId
                .systemDefault()).toLocalDate());
        funcionarioRepository.save(funcionario);
        System.out.println("\nSalvo");

    }

    private void visualizar(Scanner scanner) {
        System.out.println("Qual pagina você deseja visualizar");
        Integer page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "salario"));

        Page<Funcionario> funcionario = funcionarioRepository.findAll(pageable);
        System.out.println(funcionario);
        System.out.println("Pagina atual " + funcionario.getNumber());
        System.out.println("Total elemento " + funcionario.getTotalElements());

        funcionario.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o id do cadastro a ser excluido: ");
        int id = scanner.nextInt();
        funcionarioRepository.deleteById(id);
        System.out.println("Excluido");
    }

}
