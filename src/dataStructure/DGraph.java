package dataStructure;

import java.util.*;

public class DGraph implements graph{
	private final Map<Integer, edge_data> edgeDataMap = new HashMap<>();
	private final Map<node_data, Set<edge_data>> nodeDataEdgeMap =  new HashMap<>();
	private final List<node_data> nodeDataList = new ArrayList<>();
    @Override
	public node_data getNode(int key) {
		node_data nodeData = null;

		for(node_data cur : this.nodeDataList) {
			if(cur.getKey() == key) {
				nodeData = cur;
				break;
			}
		}
		return nodeData;
	}
	@Override
	public edge_data getEdge(int src, int dest) {
		return this.edgeDataMap.get(Edge.hashCode(src, dest));
	}

	@Override
	public void addNode(node_data n) {
		this.nodeDataList.add(n);
		this.nodeDataEdgeMap.put(n, new HashSet<>());
	}

	@Override
	public void connect(int src, int dest, double w) {
        Edge edge = new Edge(src, dest, w);
        this.edgeDataMap.put(Edge.hashCode(src, dest), edge);
        this.nodeDataEdgeMap.get(getNode(src)).add(edge);
	}

	@Override
	public Collection<node_data> getV() {
        return this.nodeDataList;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.nodeDataEdgeMap.get(getNode(node_id));
	}

	@Override
	public node_data removeNode(int key) {
        node_data nodeDataToRemove = null;

        this.nodeDataEdgeMap.remove(getNode(key));

        for(node_data nodeData : this.nodeDataList) {
            if(nodeData.getKey() == key) {
                nodeDataToRemove = nodeData;
                break;
            }
        }

        if(nodeDataToRemove == null) {
            return null;
        } else {//??
            this.nodeDataList.remove(nodeDataToRemove);

            for(node_data nodeData : this.nodeDataList) {
                // check edge (nodeDataToRemove, nodeData)
                int edgeHashcodeSrc = Edge.hashCode(nodeDataToRemove.getKey(), nodeData.getKey());

                if(this.edgeDataMap.containsKey(edgeHashcodeSrc)) {
                    this.edgeDataMap.remove(edgeHashcodeSrc);
                }

                // check edge (nodeData, nodeDataToRemove)

                edgeHashcodeSrc = Edge.hashCode(nodeData.getKey(), nodeDataToRemove.getKey());

                if(this.edgeDataMap.containsKey(edgeHashcodeSrc)) {
                    this.edgeDataMap.remove(edgeHashcodeSrc);
                }
            }

            return nodeDataToRemove;
        }
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		int edgeHashcode = Edge.hashCode(src, dest);

		if(this.edgeDataMap.containsKey(edgeHashcode)) {
		    edge_data edgeData = this.edgeDataMap.get(edgeHashcode);
		    this.edgeDataMap.remove(edgeHashcode);
            this.nodeDataEdgeMap.get(getNode(src)).remove(edgeData);
		    return edgeData;
        } else {
		    return null;
        }
	}

	@Override
	public int nodeSize() {
	    return this.nodeDataList.size();
	}

	@Override
	public int edgeSize() {
		return this.nodeDataEdgeMap.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
        DGraph graph  =new DGraph();
        graph.addNode(new Node(1, null, 3, null, 0));
        graph.addNode(new Node(2, null, 3, null, 0));
		graph.addNode(new Node(3, null, 3, null, 0));
        graph.connect(1, 2, 5);
		graph.connect(2, 3, 5);
		graph.connect(3, 1, 5);
		graph.removeNode(1);
		int x=graph.nodeSize();
		System.out.println(x);
		graph.removeNode(2);
		x=graph.nodeSize();
		System.out.println(x);
		int y=graph.edgeSize();
		System.out.println(y);

	}

}
