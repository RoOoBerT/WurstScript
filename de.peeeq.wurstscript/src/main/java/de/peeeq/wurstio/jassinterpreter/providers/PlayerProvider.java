package de.peeeq.wurstio.jassinterpreter.providers;

import de.peeeq.wurstio.jassinterpreter.mocks.PlayerMock;
import de.peeeq.wurstscript.intermediatelang.ILconstInt;
import de.peeeq.wurstscript.intermediatelang.IlConstHandle;
import de.peeeq.wurstscript.intermediatelang.interpreter.ILInterpreter;

public class PlayerProvider extends Provider {
    public PlayerProvider(ILInterpreter interpreter) {
        super(interpreter);
    }

    public IlConstHandle Player(ILconstInt p) {
        return new IlConstHandle("Player" + p.getVal(), new PlayerMock(p));
    }

    public ILconstInt GetPlayerId(IlConstHandle p) {
        return p != null ? ((PlayerMock)p.getObj()).id : ILconstInt.create(-1);
    }

    public IlConstHandle GetLocalPlayer() {
        return new IlConstHandle("Local Player", "local player");
    }
}
