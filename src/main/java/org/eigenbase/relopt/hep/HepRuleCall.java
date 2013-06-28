/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package org.eigenbase.relopt.hep;

import java.util.*;

import org.eigenbase.rel.*;
import org.eigenbase.relopt.*;


/**
 * HepRuleCall implements {@link RelOptRuleCall} for a {@link HepPlanner}. It
 * remembers transformation results so that the planner can choose which one (if
 * any) should replace the original expression.
 *
 * @author John V. Sichi
 * @version $Id$
 */
public class HepRuleCall
    extends RelOptRuleCall
{
    //~ Instance fields --------------------------------------------------------

    private List<RelNode> results;

    //~ Constructors -----------------------------------------------------------

    HepRuleCall(
        RelOptPlanner planner,
        RelOptRuleOperand operand,
        RelNode [] rels,
        Map<RelNode, List<RelNode>> nodeChildren,
        List<RelNode> parents)
    {
        super(planner, operand, rels, nodeChildren, parents);

        results = new ArrayList<RelNode>();
    }

    //~ Methods ----------------------------------------------------------------

    // implement RelOptRuleCall
    public void transformTo(RelNode rel)
    {
        final RelNode rel0 = rels[0];
        RelOptUtil.verifyTypeEquivalence(rel0, rel, rel0);
        results.add(rel);
    }

    List<RelNode> getResults()
    {
        return results;
    }
}

// End HepRuleCall.java
