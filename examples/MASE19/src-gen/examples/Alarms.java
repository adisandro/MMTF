/**
 * Generated from platform:/resource/MASE19/src/examples/queries.vql
 */
package examples;

import edu.toronto.cs.se.modelepedia.classdiagram.NamedElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;
import org.eclipse.viatra.query.runtime.api.IQuerySpecification;
import org.eclipse.viatra.query.runtime.api.ViatraQueryEngine;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFPQuery;
import org.eclipse.viatra.query.runtime.api.impl.BaseGeneratedEMFQuerySpecification;
import org.eclipse.viatra.query.runtime.api.impl.BaseMatcher;
import org.eclipse.viatra.query.runtime.api.impl.BasePatternMatch;
import org.eclipse.viatra.query.runtime.emf.types.EClassTransitiveInstancesKey;
import org.eclipse.viatra.query.runtime.emf.types.EDataTypeInSlotsKey;
import org.eclipse.viatra.query.runtime.emf.types.EStructuralFeatureInstancesKey;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.matchers.psystem.IExpressionEvaluator;
import org.eclipse.viatra.query.runtime.matchers.psystem.IValueProvider;
import org.eclipse.viatra.query.runtime.matchers.psystem.PBody;
import org.eclipse.viatra.query.runtime.matchers.psystem.PVariable;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.Equality;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExportedParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicdeferred.ExpressionEvaluation;
import org.eclipse.viatra.query.runtime.matchers.psystem.basicenumerables.TypeConstraint;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameter;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PParameterDirection;
import org.eclipse.viatra.query.runtime.matchers.psystem.queries.PVisibility;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuple;
import org.eclipse.viatra.query.runtime.matchers.tuple.Tuples;
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil;

/**
 * A pattern-specific query specification that can instantiate Matcher in a type-safe way.
 * 
 * <p>Original source:
 *         <code><pre>
 *         pattern alarms(classElem: NamedElement) {
 *           NamedElement.name(classElem, elemName);
 *           check(elemName.toLowerCase().contains("alarm"));
 *         }
 * </pre></code>
 * 
 * @see Matcher
 * @see Match
 * 
 */
@SuppressWarnings("all")
public final class Alarms extends BaseGeneratedEMFQuerySpecification<Alarms.Matcher> {
  /**
   * Pattern-specific match representation of the examples.alarms pattern,
   * to be used in conjunction with {@link Matcher}.
   * 
   * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
   * Each instance is a (possibly partial) substitution of pattern parameters,
   * usable to represent a match of the pattern in the result of a query,
   * or to specify the bound (fixed) input parameters when issuing a query.
   * 
   * @see Matcher
   * 
   */
  public static abstract class Match extends BasePatternMatch {
    private NamedElement fClassElem;
    
    private static List<String> parameterNames = makeImmutableList("classElem");
    
    private Match(final NamedElement pClassElem) {
      this.fClassElem = pClassElem;
    }
    
    @Override
    public Object get(final String parameterName) {
      switch(parameterName) {
          case "classElem": return this.fClassElem;
          default: return null;
      }
    }
    
    @Override
    public Object get(final int index) {
      switch(index) {
          case 0: return this.fClassElem;
          default: return null;
      }
    }
    
    public NamedElement getClassElem() {
      return this.fClassElem;
    }
    
    @Override
    public boolean set(final String parameterName, final Object newValue) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      if ("classElem".equals(parameterName) ) {
          this.fClassElem = (NamedElement) newValue;
          return true;
      }
      return false;
    }
    
    public void setClassElem(final NamedElement pClassElem) {
      if (!isMutable()) throw new java.lang.UnsupportedOperationException();
      this.fClassElem = pClassElem;
    }
    
    @Override
    public String patternName() {
      return "examples.alarms";
    }
    
    @Override
    public List<String> parameterNames() {
      return Alarms.Match.parameterNames;
    }
    
    @Override
    public Object[] toArray() {
      return new Object[]{fClassElem};
    }
    
    @Override
    public Alarms.Match toImmutable() {
      return isMutable() ? newMatch(fClassElem) : this;
    }
    
    @Override
    public String prettyPrint() {
      StringBuilder result = new StringBuilder();
      result.append("\"classElem\"=" + prettyPrintValue(fClassElem));
      return result.toString();
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(fClassElem);
    }
    
    @Override
    public boolean equals(final Object obj) {
      if (this == obj)
          return true;
      if (obj == null) {
          return false;
      }
      if ((obj instanceof Alarms.Match)) {
          Alarms.Match other = (Alarms.Match) obj;
          return Objects.equals(fClassElem, other.fClassElem);
      } else {
          // this should be infrequent
          if (!(obj instanceof IPatternMatch)) {
              return false;
          }
          IPatternMatch otherSig  = (IPatternMatch) obj;
          return Objects.equals(specification(), otherSig.specification()) && Arrays.deepEquals(toArray(), otherSig.toArray());
      }
    }
    
    @Override
    public Alarms specification() {
      return Alarms.instance();
    }
    
    /**
     * Returns an empty, mutable match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @return the empty match.
     * 
     */
    public static Alarms.Match newEmptyMatch() {
      return new Mutable(null);
    }
    
    /**
     * Returns a mutable (partial) match.
     * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
     * 
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return the new, mutable (partial) match object.
     * 
     */
    public static Alarms.Match newMutableMatch(final NamedElement pClassElem) {
      return new Mutable(pClassElem);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public static Alarms.Match newMatch(final NamedElement pClassElem) {
      return new Immutable(pClassElem);
    }
    
    private static final class Mutable extends Alarms.Match {
      Mutable(final NamedElement pClassElem) {
        super(pClassElem);
      }
      
      @Override
      public boolean isMutable() {
        return true;
      }
    }
    
    private static final class Immutable extends Alarms.Match {
      Immutable(final NamedElement pClassElem) {
        super(pClassElem);
      }
      
      @Override
      public boolean isMutable() {
        return false;
      }
    }
  }
  
  /**
   * Generated pattern matcher API of the examples.alarms pattern,
   * providing pattern-specific query methods.
   * 
   * <p>Use the pattern matcher on a given model via {@link #on(ViatraQueryEngine)},
   * e.g. in conjunction with {@link ViatraQueryEngine#on(QueryScope)}.
   * 
   * <p>Matches of the pattern will be represented as {@link Match}.
   * 
   * <p>Original source:
   * <code><pre>
   * pattern alarms(classElem: NamedElement) {
   *   NamedElement.name(classElem, elemName);
   *   check(elemName.toLowerCase().contains("alarm"));
   * }
   * </pre></code>
   * 
   * @see Match
   * @see Alarms
   * 
   */
  public static class Matcher extends BaseMatcher<Alarms.Match> {
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    public static Alarms.Matcher on(final ViatraQueryEngine engine) {
      // check if matcher already exists
      Matcher matcher = engine.getExistingMatcher(querySpecification());
      if (matcher == null) {
          matcher = (Matcher)engine.getMatcher(querySpecification());
      }
      return matcher;
    }
    
    /**
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * @return an initialized matcher
     * @noreference This method is for internal matcher initialization by the framework, do not call it manually.
     * 
     */
    public static Alarms.Matcher create() {
      return new Matcher();
    }
    
    private static final int POSITION_CLASSELEM = 0;
    
    private static final Logger LOGGER = ViatraQueryLoggingUtil.getLogger(Alarms.Matcher.class);
    
    /**
     * Initializes the pattern matcher within an existing VIATRA Query engine.
     * If the pattern matcher is already constructed in the engine, only a light-weight reference is returned.
     * 
     * @param engine the existing VIATRA Query engine in which this matcher will be created.
     * @throws ViatraQueryRuntimeException if an error occurs during pattern matcher creation
     * 
     */
    private Matcher() {
      super(querySpecification());
    }
    
    /**
     * Returns the set of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return matches represented as a Match object.
     * 
     */
    public Collection<Alarms.Match> getAllMatches(final NamedElement pClassElem) {
      return rawStreamAllMatches(new Object[]{pClassElem}).collect(Collectors.toSet());
    }
    
    /**
     * Returns a stream of all matches of the pattern that conform to the given fixed values of some parameters.
     * </p>
     * <strong>NOTE</strong>: It is important not to modify the source model while the stream is being processed.
     * If the match set of the pattern changes during processing, the contents of the stream is <strong>undefined</strong>.
     * In such cases, either rely on {@link #getAllMatches()} or collect the results of the stream in end-user code.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return a stream of matches represented as a Match object.
     * 
     */
    public Stream<Alarms.Match> streamAllMatches(final NamedElement pClassElem) {
      return rawStreamAllMatches(new Object[]{pClassElem});
    }
    
    /**
     * Returns an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return a match represented as a Match object, or null if no match is found.
     * 
     */
    public Optional<Alarms.Match> getOneArbitraryMatch(final NamedElement pClassElem) {
      return rawGetOneArbitraryMatch(new Object[]{pClassElem});
    }
    
    /**
     * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
     * under any possible substitution of the unspecified parameters (if any).
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return true if the input is a valid (partial) match of the pattern.
     * 
     */
    public boolean hasMatch(final NamedElement pClassElem) {
      return rawHasMatch(new Object[]{pClassElem});
    }
    
    /**
     * Returns the number of all matches of the pattern that conform to the given fixed values of some parameters.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return the number of pattern matches found.
     * 
     */
    public int countMatches(final NamedElement pClassElem) {
      return rawCountMatches(new Object[]{pClassElem});
    }
    
    /**
     * Executes the given processor on an arbitrarily chosen match of the pattern that conforms to the given fixed values of some parameters.
     * Neither determinism nor randomness of selection is guaranteed.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @param processor the action that will process the selected match.
     * @return true if the pattern has at least one match with the given parameter values, false if the processor was not invoked
     * 
     */
    public boolean forOneArbitraryMatch(final NamedElement pClassElem, final Consumer<? super Alarms.Match> processor) {
      return rawForOneArbitraryMatch(new Object[]{pClassElem}, processor);
    }
    
    /**
     * Returns a new (partial) match.
     * This can be used e.g. to call the matcher with a partial match.
     * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
     * @param pClassElem the fixed value of pattern parameter classElem, or null if not bound.
     * @return the (partial) match object.
     * 
     */
    public Alarms.Match newMatch(final NamedElement pClassElem) {
      return Alarms.Match.newMatch(pClassElem);
    }
    
    /**
     * Retrieve the set of values that occur in matches for classElem.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    protected Stream<NamedElement> rawStreamAllValuesOfclassElem(final Object[] parameters) {
      return rawStreamAllValues(POSITION_CLASSELEM, parameters).map(NamedElement.class::cast);
    }
    
    /**
     * Retrieve the set of values that occur in matches for classElem.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Set<NamedElement> getAllValuesOfclassElem() {
      return rawStreamAllValuesOfclassElem(emptyArray()).collect(Collectors.toSet());
    }
    
    /**
     * Retrieve the set of values that occur in matches for classElem.
     * @return the Set of all values or empty set if there are no matches
     * 
     */
    public Stream<NamedElement> streamAllValuesOfclassElem() {
      return rawStreamAllValuesOfclassElem(emptyArray());
    }
    
    @Override
    protected Alarms.Match tupleToMatch(final Tuple t) {
      try {
          return Alarms.Match.newMatch((NamedElement) t.get(POSITION_CLASSELEM));
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in tuple not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Alarms.Match arrayToMatch(final Object[] match) {
      try {
          return Alarms.Match.newMatch((NamedElement) match[POSITION_CLASSELEM]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    @Override
    protected Alarms.Match arrayToMatchMutable(final Object[] match) {
      try {
          return Alarms.Match.newMutableMatch((NamedElement) match[POSITION_CLASSELEM]);
      } catch(ClassCastException e) {
          LOGGER.error("Element(s) in array not properly typed!",e);
          return null;
      }
    }
    
    /**
     * @return the singleton instance of the query specification of this pattern
     * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
     * 
     */
    public static IQuerySpecification<Alarms.Matcher> querySpecification() {
      return Alarms.instance();
    }
  }
  
  private Alarms() {
    super(GeneratedPQuery.INSTANCE);
  }
  
  /**
   * @return the singleton instance of the query specification
   * @throws ViatraQueryRuntimeException if the pattern definition could not be loaded
   * 
   */
  public static Alarms instance() {
    try{
        return LazyHolder.INSTANCE;
    } catch (ExceptionInInitializerError err) {
        throw processInitializerError(err);
    }
  }
  
  @Override
  protected Alarms.Matcher instantiate(final ViatraQueryEngine engine) {
    return Alarms.Matcher.on(engine);
  }
  
  @Override
  public Alarms.Matcher instantiate() {
    return Alarms.Matcher.create();
  }
  
  @Override
  public Alarms.Match newEmptyMatch() {
    return Alarms.Match.newEmptyMatch();
  }
  
  @Override
  public Alarms.Match newMatch(final Object... parameters) {
    return Alarms.Match.newMatch((edu.toronto.cs.se.modelepedia.classdiagram.NamedElement) parameters[0]);
  }
  
  /**
   * Inner class allowing the singleton instance of {@link Alarms} to be created 
   *     <b>not</b> at the class load time of the outer class, 
   *     but rather at the first call to {@link Alarms#instance()}.
   * 
   * <p> This workaround is required e.g. to support recursion.
   * 
   */
  private static class LazyHolder {
    private static final Alarms INSTANCE = new Alarms();
    
    /**
     * Statically initializes the query specification <b>after</b> the field {@link #INSTANCE} is assigned.
     * This initialization order is required to support indirect recursion.
     * 
     * <p> The static initializer is defined using a helper field to work around limitations of the code generator.
     * 
     */
    private static final Object STATIC_INITIALIZER = ensureInitialized();
    
    public static Object ensureInitialized() {
      INSTANCE.ensureInitializedInternal();
      return null;
    }
  }
  
  private static class GeneratedPQuery extends BaseGeneratedEMFPQuery {
    private static final Alarms.GeneratedPQuery INSTANCE = new GeneratedPQuery();
    
    private final PParameter parameter_classElem = new PParameter("classElem", "edu.toronto.cs.se.modelepedia.classdiagram.NamedElement", new EClassTransitiveInstancesKey((EClass)getClassifierLiteralSafe("http://se.cs.toronto.edu/modelepedia/ClassDiagram", "NamedElement")), PParameterDirection.INOUT);
    
    private final List<PParameter> parameters = Arrays.asList(parameter_classElem);
    
    private GeneratedPQuery() {
      super(PVisibility.PUBLIC);
    }
    
    @Override
    public String getFullyQualifiedName() {
      return "examples.alarms";
    }
    
    @Override
    public List<String> getParameterNames() {
      return Arrays.asList("classElem");
    }
    
    @Override
    public List<PParameter> getParameters() {
      return parameters;
    }
    
    @Override
    public Set<PBody> doGetContainedBodies() {
      setEvaluationHints(new QueryEvaluationHint(null, QueryEvaluationHint.BackendRequirement.UNSPECIFIED));
      Set<PBody> bodies = new LinkedHashSet<>();
      {
          PBody body = new PBody(this);
          PVariable var_classElem = body.getOrCreateVariableByName("classElem");
          PVariable var_elemName = body.getOrCreateVariableByName("elemName");
          new TypeConstraint(body, Tuples.flatTupleOf(var_classElem), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://se.cs.toronto.edu/modelepedia/ClassDiagram", "NamedElement")));
          body.setSymbolicParameters(Arrays.<ExportedParameter>asList(
             new ExportedParameter(body, var_classElem, parameter_classElem)
          ));
          //   NamedElement.name(classElem, elemName)
          new TypeConstraint(body, Tuples.flatTupleOf(var_classElem), new EClassTransitiveInstancesKey((EClass)getClassifierLiteral("http://se.cs.toronto.edu/modelepedia/ClassDiagram", "NamedElement")));
          PVariable var__virtual_0_ = body.getOrCreateVariableByName(".virtual{0}");
          new TypeConstraint(body, Tuples.flatTupleOf(var_classElem, var__virtual_0_), new EStructuralFeatureInstancesKey(getFeatureLiteral("http://se.cs.toronto.edu/modelepedia/ClassDiagram", "NamedElement", "name")));
          new TypeConstraint(body, Tuples.flatTupleOf(var__virtual_0_), new EDataTypeInSlotsKey((EDataType)getClassifierLiteral("http://www.eclipse.org/emf/2002/Ecore", "EString")));
          new Equality(body, var__virtual_0_, var_elemName);
          //   check(elemName.toLowerCase().contains("alarm"))
          new ExpressionEvaluation(body, new IExpressionEvaluator() {
          
              @Override
              public String getShortDescription() {
                  return "Expression evaluation from pattern alarms";
              }
              
              @Override
              public Iterable<String> getInputParameterNames() {
                  return Arrays.asList("elemName");}
          
              @Override
              public Object evaluateExpression(IValueProvider provider) throws Exception {
                  String elemName = (String) provider.getValue("elemName");
                  return evaluateExpression_1_1(elemName);
              }
          },  null); 
          bodies.add(body);
      }
      return bodies;
    }
  }
  
  private static boolean evaluateExpression_1_1(final String elemName) {
    boolean _contains = elemName.toLowerCase().contains("alarm");
    return _contains;
  }
}
