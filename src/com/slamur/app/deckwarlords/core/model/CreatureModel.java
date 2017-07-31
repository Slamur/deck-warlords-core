package com.slamur.app.deckwarlords.core.model;

import com.slamur.app.deckwarlords.core.card.Creature;
import com.slamur.app.deckwarlords.core.card.Token;
import com.slamur.app.deckwarlords.core.info.CreatureInfo;
import com.slamur.app.deckwarlords.core.info.creature.Creatures;
import com.slamur.lib.file.Extensions;
import com.slamur.lib.model.Provider;
import com.slamur.lib.model.impl.ModelImpl;
import com.slamur.lib.model.provider.FileProvider;

import java.io.PrintWriter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class CreatureModel extends ModelImpl<Creature> {

    public static final String DEFAULT_CREATURES_FILE_NAME = "Creatures with tokens";

    public static CreatureModel createInstance() {
        List<Token> tokens = Token.generateTokens();

        FileProvider.FileStrategy<Creature> creatureFileStrategy = new FileProvider.FileStrategy<Creature>() {
            @Override
            public Creature readValue(StringTokenizer stringTokenizer) {
                String name = stringTokenizer.nextToken();
                int stars = Integer.parseInt(stringTokenizer.nextToken());

                Creature creature = null;

                for (CreatureInfo[] creaturesArray : Creatures.CREATURES) {
                    for (CreatureInfo creatureInfo : creaturesArray) {
                        if (creatureInfo.getName().equals(name)) {
                            creature = new Creature(creatureInfo, stars);
                        }
                    }
                }

                if (creature != null) {
                    for (int tokenIndex = 0; tokenIndex < creature.getMaxTokens(); ++tokenIndex) {
                        try {
                            String tokenName = stringTokenizer.nextToken();
                            if (tokenName.isEmpty()) continue;

                            Token selectedToken = tokens.stream()
                                    .filter(token -> token.toString().equals(tokenName))
                                    .findFirst().get();

                            creature.setToken(tokenIndex, selectedToken);
                        } catch (NoSuchElementException ignored) {
                        }
                    }
                }

                return creature;
            }

            @Override
            public void printValue(PrintWriter out, Creature creature) {
                out.print(creature.getName());
                out.print(Tsv.DELIMITER + creature.getStars());

                for (int tokenIndex = 0; tokenIndex < creature.getMaxTokens(); ++tokenIndex) {
                    Token token = creature.getToken(tokenIndex);

                    out.print(Tsv.DELIMITER);
                    if (null != token) {
                        out.print(token.toString());
                    }
                }
            }
        };

        return createInstance(creatureFileStrategy);
    }

    public static CreatureModel createInstance(FileProvider.FileStrategy<Creature> creatureFileStrategy) {
        FileProvider<Creature> creatureFileProvider = new FileProvider<>(creatureFileStrategy);
        creatureFileProvider.setFileName(DEFAULT_CREATURES_FILE_NAME);
        creatureFileProvider.setDelimiter(Extensions.Tsv.DELIMITER);
        creatureFileProvider.setExtension(Extensions.Tsv.EXTENSION);

        return createInstance(creatureFileProvider);
    }

    public static CreatureModel createInstance(Provider<Creature> creatureProvider) {
        CreatureModel creatureModel = new CreatureModel();
        creatureModel.setProvider(creatureProvider);

        return creatureModel;
    }

    private CreatureModel() {
        super();
    }

    @Override
    public void updateValues() {
        List<Creature> updatedCreatures = getProvider().loadValues();
        setValues(updatedCreatures);
    }
}
