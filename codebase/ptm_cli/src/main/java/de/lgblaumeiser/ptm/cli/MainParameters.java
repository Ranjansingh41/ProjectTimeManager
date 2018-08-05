/*
 * Copyright by Lars Geyer-Blaumeiser <lgblaumeiser@gmail.com>
 *
 * Licensed under MIT license
 */
package de.lgblaumeiser.ptm.cli;

import com.beust.jcommander.Parameter;

public class MainParameters {
    @Parameter(names = { "-h", "--help" }, help = true)
    private boolean help = false;

    public boolean helpNeeded() {
        return help;
    }
}
