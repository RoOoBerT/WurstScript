package de.peeeq.wurstscript.types;

import de.peeeq.wurstscript.ast.Element;
import de.peeeq.wurstscript.ast.TypeParamDef;
import de.peeeq.wurstscript.jassIm.ImExprOpt;
import de.peeeq.wurstscript.jassIm.ImType;
import fj.data.TreeMap;
import org.eclipse.jdt.annotation.Nullable;

public class WurstTypeStaticTypeRef extends WurstType {

    private final WurstType base;

    public WurstTypeStaticTypeRef(WurstType base) {
        this.base = base;
    }

    @Override
    @Nullable TreeMap<TypeParamDef, WurstTypeBoundTypeParam> matchAgainstSupertypeIntern(WurstType other, @Nullable Element location, Collection<TypeParamDef> typeParams, TreeMap<TypeParamDef, WurstTypeBoundTypeParam> mapping) {
        if (other instanceof WurstTypeStaticTypeRef) {
            return base.isSubtypeOf(((WurstTypeStaticTypeRef) other).base, location);
        }
        return false;
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public String getFullName() {
        return "static reference to " + base.getFullName();
    }

    @Override
    public ImType imTranslateType() {
        return base.imTranslateType();
    }

    @Override
    public ImExprOpt getDefaultValue() {
        return base.getDefaultValue();
    }

    @Override
    public WurstType dynamic() {
        return base;
    }

}
