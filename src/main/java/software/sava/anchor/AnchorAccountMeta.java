package software.sava.anchor;

import software.sava.core.accounts.PublicKey;

import java.util.List;

public record AnchorAccountMeta(List<AnchorAccountMeta> accounts,
                                PublicKey address,
                                String name,
                                boolean writable,
                                boolean signer,
                                String description,
                                List<String> docs,
                                boolean isOptional,
                                AnchorPDA pda,
                                List<String> relations) {

  public String docComments() {
    return AnchorNamedType.formatComments(this.docs);
  }
}
