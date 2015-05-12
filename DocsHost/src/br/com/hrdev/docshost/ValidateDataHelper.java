package br.com.hrdev.docshost;

import br.com.hrdev.shared.docs.exceptions.InputDataException;
import br.com.hrdev.shared.docs.models.Usuario;

/**
 *
 * @author 180901954
 */
public class ValidateDataHelper {

    public static void checkCadastroInputData(Usuario usuario) throws InputDataException {
        if (hasSizePassword(usuario.getSenha()) == false) {
            throw new InputDataException("A senha deve ter de 6 a 12 caracteres");
        }

        if (hasSizeUsername(usuario.getNome()) == false) {
            throw new InputDataException("O nome deve ter de 3 a 20 caracteres");
        }
    }

    private static boolean hasSizePassword(String senha) {
        int size = senha.length();
        return (size <= 12 && size >= 6);
    }

    private static boolean hasSizeUsername(String username) {
        int size = username.length();
        return (size <= 20 && size >= 3);
    }

}
