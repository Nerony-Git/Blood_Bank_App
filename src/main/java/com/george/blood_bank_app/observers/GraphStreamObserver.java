package com.george.blood_bank_app.observers;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphwalker.core.event.EventType;
import org.graphwalker.core.event.Observer;
import org.graphwalker.core.machine.Context;
import org.graphwalker.core.machine.Machine;
import org.graphwalker.core.model.Element;
import org.graphwalker.core.model.Vertex;

public class GraphStreamObserver implements Observer {
    private final Graph graph;
    private Element lastElement = null;
    private Context lastContext = null;

    private static String stylesheet = "" +
            "node {" +
            " shape: rounded-box;" +
            " fill-color: grey;" +
            " fill-mode: dyn-plain;" +
            " size-mode: fit;" +
            "}" +
            "node.active {" +
            " fill-color: green;" +
            " size:40px;" +
            "}" +
            "edge {" +
            " shape: cubic-curve;" +
            " size: 1px;" +
            " arrow-shape: arrow;" +
            "}" +
            "edge.active {" +
            " fill-color: green;" +
            " size: 5px;" +
            "}";

    public GraphStreamObserver(Graph graph) {
        this.graph = graph;
        this.graph.setAttribute("ui.quality");
        this.graph.setAttribute("ui.antialias");
        this.graph.setAttribute("ui.stylesheet", stylesheet);
    }

    @Override
    public void update(Machine machine, Element element, EventType eventType) {
        if (EventType.BEFORE_ELEMENT.equals(eventType)) {

            if (lastElement != null) {
                if (lastElement instanceof Vertex.RuntimeVertex) {
                    graph.getNode(getId(lastContext, lastElement)).removeAttribute("ui.class");
                } else {
                    graph.getEdge(getId(lastContext, lastElement)).removeAttribute("ui.class");
                }
            }

            if (element instanceof Vertex.RuntimeVertex) {
                Vertex.RuntimeVertex vertex = (Vertex.RuntimeVertex) element;
                if (null == graph.getNode(getId(machine.getCurrentContext(), vertex))) {
                    Node node = graph.addNode(getId(machine.getCurrentContext(), vertex));
                    node.setAttribute("ui.label", vertex.getName());
                    if (vertex.hasSharedState()) {
                        node.setAttribute("sharedState", vertex.getSharedState());
                    }
                }
                graph.getNode(getId(machine.getCurrentContext(), vertex)).setAttribute("ui.class", "active");

                // Check whether we should draw an edge between 2 shared vertices
                if (vertex.hasSharedState() &&
                        lastElement instanceof Vertex.RuntimeVertex &&
                        ((Vertex.RuntimeVertex) lastElement).hasSharedState() &&
                        machine.getCurrentContext() != lastContext) {
                    if (null == graph.getEdge(getId(lastContext, lastElement) + getId(machine.getCurrentContext(), vertex))) {
                        graph.addEdge(getId(lastContext, lastElement) + getId(machine.getCurrentContext(), vertex),
                                (Node)graph.getNode(getId(lastContext, lastElement)),
                                (Node)graph.getNode(getId(machine.getCurrentContext(), vertex)), true);
                    }
                }
            } else {
                org.graphwalker.core.model.Edge.RuntimeEdge edge = (org.graphwalker.core.model.Edge.RuntimeEdge) element;
                if (null == graph.getEdge(getId(machine.getCurrentContext(), edge))) {
                    String source;
                    if (null == edge.getSourceVertex() && null == graph.getEdge("Start")) {
                        Node node = graph.addNode("Start");
                        node.setAttribute("ui.label", "Start");
                        source = "Start";
                    } else {
                        source = getId(machine.getCurrentContext(), edge.getSourceVertex());
                    }
                    if (null == graph.getNode(getId(machine.getCurrentContext(), edge.getTargetVertex()))) {
                        Node node = graph.addNode(getId(machine.getCurrentContext(), edge.getTargetVertex()));
                        node.setAttribute("ui.label", edge.getTargetVertex().getName());
                    }
                    Edge arc = graph.addEdge(getId(machine.getCurrentContext(), edge), source, getId(machine.getCurrentContext(), edge.getTargetVertex()), true);
                    arc.setAttribute("ui.label", edge.getName());
                }
                graph.getEdge(getId(machine.getCurrentContext(), edge)).setAttribute("ui.class", "active");
            }
            lastElement = element;
            lastContext = machine.getCurrentContext();
        }
    }

    private String getId(Context context, Element element) {
        return context.toString() + element.getId();
    }
}
