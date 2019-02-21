package ckmbks.framework.text.replacer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ckmbks.framework.lang.Chain;
import ckmbks.framework.text.StrBuilder;
import ckmbks.framework.text.replacer.StrReplacer;

/**
 * 字符串替换链，用于组合多个字符串替换逻辑
 * 
 * @author looly
 * @since 4.1.5
 */
public class ReplacerChain extends ckmbks.framework.text.replacer.StrReplacer implements Chain<ckmbks.framework.text.replacer.StrReplacer, ReplacerChain> {

	private List<ckmbks.framework.text.replacer.StrReplacer> replacers = new LinkedList<>();

	/**
	 * 构造
	 * 
	 * @param strReplacers 字符串替换器
	 */
	public ReplacerChain(ckmbks.framework.text.replacer.StrReplacer... strReplacers) {
		for (ckmbks.framework.text.replacer.StrReplacer strReplacer : strReplacers) {
			addChain(strReplacer);
		}
	}

	@Override
	public Iterator<ckmbks.framework.text.replacer.StrReplacer> iterator() {
		return replacers.iterator();
	}

	@Override
	public ReplacerChain addChain(ckmbks.framework.text.replacer.StrReplacer element) {
		replacers.add(element);
		return this;
	}

	@Override
	protected int replace(CharSequence str, int pos, StrBuilder out) {
		int consumed = 0;
		for (StrReplacer strReplacer : replacers) {
			consumed = strReplacer.replace(str, pos, out);
			if (0 != consumed) {
				return consumed;
			}
		}
		return consumed;
	}

}
