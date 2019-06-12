package data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import database.DatabaseConnectionException;
import database.DbAccess;
import database.EmptySetException;
import database.Example;
import database.NoValueException;
import database.QUERY_TYPE;
import database.TableData;
import database.TableSchema;

public class Data {

	private List<Example> data = new ArrayList<Example>();
	private int numberOfExamples;
	private List<Attribute> attributeSet = new LinkedList<Attribute>();

	public Data(final String table) {
		DbAccess db = new DbAccess();
		TableData t_data = new TableData(db);
		try {
			db.initConnection();
			data = t_data.getDistinctTransazioni(table);
			TableSchema t_schema = new TableSchema(db, table);
			numberOfExamples = data.size();

			for (int i = 0; i < t_schema.getNumberOfAttributes(); i++) {
				if (t_schema.getColumn(i).isNumber()) {
					attributeSet.add(new ContinuousAttribute(t_schema.getColumn(i).getColumnName(), i, 
							(float) t_data.getAggregateColumnValue(table, t_schema.getColumn(i), QUERY_TYPE.MIN), 
							(float) t_data.getAggregateColumnValue(table, t_schema.getColumn(i), QUERY_TYPE.MAX)));
				} else {
					Set<Object> results = t_data.getDistinctColumnValues(table, t_schema.getColumn(i));
					String[] attributes_values = new String[results.size()];
					int j = 0;
					for (Object o: results) {
						attributes_values[j] = (String) o;
						j++;
					}
					attributeSet.add(new DiscreteAttribute(t_schema.getColumn(i).getColumnName(), i,
							t_data.getDistinctColumnValues(table, t_schema.getColumn(i))
									.toArray(new String[0])));
				}
			}
			System.out.println(" ================= ");
			System.out.println(attributeSet);
			System.out.println(" ================= ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (EmptySetException e) {
			e.printStackTrace();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		} catch (NoValueException e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	public int getNumberOfAttributes() {
		return attributeSet.size();
	}

	public Object getAttributeValue(final int exampleIndex, final int attributeIndex) {
		return data.get(exampleIndex).get(attributeIndex);
	}

	public Attribute getAttribute(final int index) {
		return attributeSet.get(index);
	}

	public List<Attribute> getAttributeSchema() {
		return attributeSet;
	}

	public Tuple getItemSet(final int index) {
		Tuple tuple = new Tuple(attributeSet.size());
		for (int i = 0; i < attributeSet.size(); i++) {
			if (attributeSet.get(i) instanceof DiscreteAttribute) {
				tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet.get(i), (String) data.get(index).get(i)), i);
			} else {
				tuple.add(new ContinuousItem(attributeSet.get(i), (double) data.get(index).get(i)), i); // dubbio
			}
		}
		return tuple;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < getNumberOfAttributes(); i++) {
			// se non avessi ridefinito il toString in Attribute, avrei la stampa di default di Object
			s += attributeSet.get(i);
			if (i != getNumberOfAttributes() - 1) {
				s += ", ";
			}
		}
		s += '\n';
		for (int i = 0; i < getNumberOfExamples(); i++) {
			s += (i + 1) + ":";
			for (int j = 0; j < getNumberOfAttributes(); j++) {
				s += getAttributeValue(i, j);
				if (j != getNumberOfAttributes() - 1) {
					s += ", ";
				}
			}
			s += "\n";
		}
		return s;
	}

	public static void main(final String[] args) {

		/*
		Data trainingSet = new Data();
		System.out.println(trainingSet);

		/*DiscreteAttribute attribute1 = (DiscreteAttribute)trainingSet.getAttribute(0);
		for (int i = 0; i < attribute1.getNumberOfDistinctValues(); i++) {
			System.out.println(attribute1.getValue(i));
		}*/

		//System.out.println(trainingSet.getItemSet(0));
		//System.out.println(trainingSet.getItemSet(1));
		//System.out.println(trainingSet.getItemSet(0).getDistance(trainingSet.getItemSet(1)));
		//System.out.println(trainingSet.getNumberOfExamples());
/*
		for (int i = 0; i < trainingSet.getNumberOfExamples(); i++) {
			if (trainingSet.getItemSet(1).getDistance(trainingSet.getItemSet(i)) <= 2.0) {
				System.out.println(trainingSet.getItemSet(0).getDistance(trainingSet.getItemSet(i)));
				System.out.println(trainingSet.getItemSet(i));
				System.out.println(i);
			}

		}

		/*for (int i = 0; i < trainingSet.getAttributeSchema().length; i++) {
		*
		*	for (int j = 0; j < trainingSet.getAttributeSchema()[i].getNumberOfDistinctValues(); j++) {
		*
		*	}
		*	System.out.println();
		}*/

	}
}
