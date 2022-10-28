package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;
    private Boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual ação de cargo deseja executar?");
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
                    visualizar();
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
        System.out.print("Descrição do cargo: ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("\nSalvo");
    }

    private void atualizar(Scanner scanner) {

        System.out.print("Digite o id do cadastro a ser atualizado: ");
        int id = scanner.nextInt();
        System.out.print("\nNovo valor: ");
        String novaDescricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(novaDescricao);

        cargoRepository.save(cargo);
        System.out.println("\nSalvo");

    }

    private void visualizar() {
        Iterable<Cargo> cargo = cargoRepository.findAll();
        cargo.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o id do cadastro a ser excluido: ");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Excluido");
    }


}
