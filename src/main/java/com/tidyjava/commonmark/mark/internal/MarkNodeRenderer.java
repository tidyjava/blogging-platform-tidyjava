package com.tidyjava.commonmark.mark.internal;

import com.tidyjava.commonmark.mark.Mark;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class MarkNodeRenderer implements NodeRenderer {

    private final HtmlNodeRendererContext context;
    private final HtmlWriter html;

    public MarkNodeRenderer(HtmlNodeRendererContext context) {
        this.context = context;
        this.html = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Mark.class);
    }

    @Override
    public void render(Node node) {
        Map<String, String> attributes = context.extendAttributes(node, Collections.emptyMap());
        html.tag("mark", attributes);
        renderChildren(node);
        html.tag("/mark");
    }

    private void renderChildren(Node parent) {
        Node node = parent.getFirstChild();
        while (node != null) {
            Node next = node.getNext();
            context.render(node);
            node = next;
        }
    }
}
