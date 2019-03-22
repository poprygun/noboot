package io.microsamples.noboot.vault;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ValutConfiguration.class)
@Slf4j
public class VaultAccessTests {

    @Autowired
    private VaultTemplate vaultTemplate;

    @Test
    public void readVaultValue(){
        VaultResponseSupport<Credentials> response = vaultTemplate
                .read("secret/vault-access", Credentials.class);

        assertThat(response.getData().getUsername()).isEqualTo("app_user");
        assertThat(response.getData().getPassword()).isEqualTo("app_password");
    }
}
